package com.codigo.giraldopalacios.controller;

import com.codigo.giraldopalacios.entidad.EmpresaEntity;
import com.codigo.giraldopalacios.request.EmpresaRequest;
import com.codigo.giraldopalacios.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/examen20240419/empresa")
@AllArgsConstructor
@Tag(
        name = "API DE MANTENIMIENTO EMPRESAS",
        description = "Esta api contiene todos los end points para realizar el mantenimiento de una empresa"
)
public class EmpresaController {

    private final EmpresaService empresaService;
    @PostMapping("/crear")
    @Operation(summary = "Crear una nueva Empresa")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Empresa Creada Exitosamente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Ocurrio un Error de Estructura de Datos",
                    content = { @Content(mediaType = "application/json")})

    })
    public ResponseEntity<EmpresaEntity> crear(@RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity.ok(empresaService.crear(empresaRequest));
    }

    @GetMapping("/buscarid/{id}")
    @Operation(summary = "Buscar una Empresa por su ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Busqueda Exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "400", description = "Parametro de Busqueda Incorrecto",
                    content = { @Content(mediaType = "application/json")})

    })
    public ResponseEntity<Optional<EmpresaEntity>> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @GetMapping("/todos")
    @Operation(summary = "Listar Todas las Empresas Registradas")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listado Exitoso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Sucedio un Error en la Conexion con la Fuente de Datos",
                    content = { @Content(mediaType = "application/json")})

    })
    public ResponseEntity<List<EmpresaEntity>> buscarTodos(){
        return ResponseEntity.ok(empresaService.buscarTodos());
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualiza una Empresa por su ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Actualizacion Exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Ocurrio un Error Actualizando Información",
                    content = { @Content(mediaType = "application/json")})
    })
    public ResponseEntity<EmpresaEntity> actualizar(@PathVariable Long id, @RequestBody EmpresaRequest empresaRequest){
        return ResponseEntity.ok(empresaService.actualizar(id,empresaRequest));
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminado Logico de una Empresa por su ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Eliminado Exitosa",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Ocurrio un Error Eliminando Empresa",
                    content = { @Content(mediaType = "application/json")})
    })
    public ResponseEntity<EmpresaEntity> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(empresaService.borrar(id));
    }





}
