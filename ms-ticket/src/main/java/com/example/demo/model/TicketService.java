package com.example.demo.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.demo.model.dto.NewTicketDTO;
import com.example.demo.model.dto.UpdateTicketStatusDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.entity.Ticket;
import com.example.demo.repository.entity.TicketStatus;

import jakarta.validation.Valid;

@Validated
@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final NotificationClient notificationClient;

    public TicketService(
        TicketRepository ticketRepository,
        NotificationClient notificationClient
    ) {
        this.ticketRepository = ticketRepository;
        this.notificationClient = notificationClient;
    }

    public Ticket create(@Valid NewTicketDTO newTicket) {
        Ticket ticket = new Ticket();

        ticket.setAction(newTicket.action());
        ticket.setObject(newTicket.object());
        ticket.setDetails(newTicket.details());
        ticket.setCreator(newTicket.creator());
        ticket.setRecipient(hasText(newTicket.recipient()) ? newTicket.recipient() : newTicket.creator());
        ticket.setObservers(new LinkedHashSet<>(safeList(newTicket.observers())));

        Ticket savedTicket = ticketRepository.save(ticket);
        notifyInterested(savedTicket);

        return savedTicket;
    }

    public Ticket updateStatus(Integer id, @Valid UpdateTicketStatusDTO update) {
        Ticket ticket = ticketRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Ticket nao encontrado"));

        if (update.status() == TicketStatus.IN_PROGRESS && !hasText(update.assignee())) {
            throw new IllegalArgumentException("O responsavel deve ser informado ao colocar o ticket em andamento");
        }

        if (update.status() == TicketStatus.CANCELED && !hasText(update.motivo())) {
            throw new IllegalArgumentException("O motivo deve ser informado ao cancelar o ticket");
        }

        if (update.status() != TicketStatus.CANCELED && hasText(update.motivo())) {
            throw new IllegalArgumentException("O motivo so pode ser informado quando o ticket for cancelado");
        }

        if (update.status() != TicketStatus.IN_PROGRESS && hasText(update.assignee())) {
            throw new IllegalArgumentException("O responsavel so pode ser informado quando o ticket for colocado em andamento");
        }

        ticket.setStatus(update.status());
        ticket.setAssignee(update.status() == TicketStatus.IN_PROGRESS ? update.assignee() : ticket.getAssignee());
        ticket.setMotivo(update.status() == TicketStatus.CANCELED ? update.motivo() : null);
        ticket.touch();

        return ticketRepository.save(ticket);
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    private void notifyInterested(Ticket ticket) {
        Set<String> recipients = new LinkedHashSet<>();
        recipients.add(ticket.getCreator());
        recipients.add(ticket.getRecipient());
        recipients.addAll(ticket.getObservers());

        for (String recipient : recipients) {
            notificationClient.send(
                recipient,
                "Novo ticket criado",
                "Ticket #%d criado para %s de %s. Detalhes: %s".formatted(
                    ticket.getId(),
                    ticket.getAction(),
                    ticket.getObject(),
                    ticket.getDetails()
                )
            );
        }
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    private List<String> safeList(List<String> values) {
        return values == null ? List.of() : values;
    }
}
