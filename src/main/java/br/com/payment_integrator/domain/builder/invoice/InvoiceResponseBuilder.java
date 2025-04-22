package br.com.payment_integrator.domain.builder.invoice;

import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.CustomerResponseDTO;
import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.InvoiceDetailsResponseDTO;
import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.MetadataResponseDTO;
import br.com.payment_integrator.domain.dto.invoice.response.FindInvoiceById.PixResponseDTO;
import br.com.payment_integrator.domain.entity.financial.Customer;
import br.com.payment_integrator.domain.entity.financial.Invoice;
import org.springframework.stereotype.Component;

@Component
public final class InvoiceResponseBuilder {

    public InvoiceDetailsResponseDTO build(Invoice invoice) {
        Customer customer = invoice.getCustomer();

        PixResponseDTO pixDto = PixResponseDTO.builder()
                .qrCode(invoice.getPixQrCode())
                .paymentLink(invoice.getPixPaymentLink())
                .build();

        CustomerResponseDTO customerDto = CustomerResponseDTO.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .build();

        MetadataResponseDTO metadataDto = MetadataResponseDTO.builder()
                .externalPaymentId(invoice.getExternalPaymentId().toString())
                .build();

        return InvoiceDetailsResponseDTO.builder()
                .id(String.valueOf(invoice.getId()))
                .status(invoice.getStatus().toString())
                .statusDetails(invoice.getStatusDetailsExternal())
                .amount(invoice.getAmount())
                .currency(invoice.getCurrency())
                .createdAt(invoice.getCreatedAt())
                .approvedAt(invoice.getApprovedAt())
                .paymentMethod(invoice.getPaymentMethod().toString())
                .pix(pixDto)
                .customer(customerDto)
                .metadata(metadataDto)
                .build();
    }
}
