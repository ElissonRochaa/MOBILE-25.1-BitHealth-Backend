package br.com.bitwise.bithealth.modules.medicamentos.handler;

import br.com.bitwise.bithealth.modules.medicamentos.exceptions.MedicamentoNotFoundException;
import br.com.bitwise.bithealth.modules.unidade_saude.exceptions.ExceptionResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class MedicamentoHandler {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de solicitação inválida")
    })
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Medicamento não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MedicamentoNotFoundException.class)))
    })
    @ExceptionHandler(MedicamentoNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleMedicamentoNotFoundException(MedicamentoNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
