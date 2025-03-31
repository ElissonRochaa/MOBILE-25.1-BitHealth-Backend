package br.com.bitwise.bithealth.modules.unidade_saude.handler;

import br.com.bitwise.bithealth.modules.unidade_saude.exceptions.EnderecoUnidadesNotFoundException;
import br.com.bitwise.bithealth.modules.unidade_saude.exceptions.ExceptionResponse;
import br.com.bitwise.bithealth.modules.unidade_saude.exceptions.UnidadeSaudeAlreadyExistsException;
import br.com.bitwise.bithealth.modules.unidade_saude.exceptions.UnidadeSaudeNotFoundException;
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
public class UnidadeSaudeHandler {

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
            @ApiResponse(responseCode = "404", description = "Unidade de saúde não encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @ExceptionHandler(UnidadeSaudeNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUnidadeSaudeNotFoundException(UnidadeSaudeNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Unidade de saúde já existe",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @ExceptionHandler(UnidadeSaudeAlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponse> handleUnidadeSaudeAlreadyExistsException(UnidadeSaudeAlreadyExistsException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Endereco da unidade de saúde não encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @ExceptionHandler(EnderecoUnidadesNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleEderecoUnidadeSaudeNotFoundException(EnderecoUnidadesNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
