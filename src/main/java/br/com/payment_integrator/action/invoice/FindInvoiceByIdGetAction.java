package br.com.payment_integrator.action.invoice;

import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.InvoiceDetailsResponseDTO;
import br.com.payment_integrator.domain.service.invoice.IFindInvoiceByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/invoice")
@RequiredArgsConstructor
public class FindInvoiceByIdGetAction {

    private final IFindInvoiceByIdService findInvoiceByIdService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<InvoiceDetailsResponseDTO> findInvoiceById(@PathVariable("id") String id) {
        return ResponseEntity.ok(findInvoiceByIdService.execute(id));
    }
}
