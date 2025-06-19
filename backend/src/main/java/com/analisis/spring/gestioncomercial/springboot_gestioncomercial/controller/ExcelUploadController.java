package com.analisis.spring.gestioncomercial.springboot_gestioncomercial.controller;

import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Convocatoria;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Convocatorias;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.ObjetoContratacion;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.model.Cliente;
import com.analisis.spring.gestioncomercial.springboot_gestioncomercial.service.ConvocatoriaService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/subida") 
//Cambien la ruta  de CrossOrigin al host local que tengan el front-end
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class ExcelUploadController {

    @Autowired
    private ConvocatoriaService convocatoriaService;

    @PostMapping("/uploadTest")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        List<Convocatoria> convocatorias = new ArrayList<>();
        List<Convocatorias> convocatorias2 = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();
        List<ObjetoContratacion> objetosContratacion = new ArrayList<>();

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío o no se envió.");
        }
        System.out.println("Archivo recibido: " + (file != null ? file.getOriginalFilename() : "null"));
        System.out.println("Tamaño del archivo: " + (file != null ? file.getSize() : 0));

        String filename = file.getOriginalFilename();
        if (filename == null) {
            return ResponseEntity.badRequest().body("El archivo no tiene nombre.");
        }

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = filename.endsWith(".xlsx") ? new XSSFWorkbook(inputStream)
                     : filename.endsWith(".xls") ? new HSSFWorkbook(inputStream) : null) {

            if (workbook == null) {
                return ResponseEntity.badRequest().body("Formato no válido (usa .xls o .xlsx)");
            }

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                return ResponseEntity.badRequest().body("El archivo no contiene hojas.");
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                return ResponseEntity.badRequest().body("El archivo no contiene encabezados.");
            }

            int prioridadCol = -1;
            for (Cell cell : headerRow) {
                if (cell.getCellType() == CellType.STRING &&
                        cell.getStringCellValue().equalsIgnoreCase("prioridad")) {
                    prioridadCol = cell.getColumnIndex();
                    break;
                }
            }

            if (prioridadCol == -1) {
                return ResponseEntity.badRequest().body("No se encontró la columna 'prioridad'");
            }

            int duplicados = 0;

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell prioridadCell = row.getCell(prioridadCol);
                double prioridadValue = 0;
                if (prioridadCell != null) {
                    if (prioridadCell.getCellType() == CellType.NUMERIC) {
                        prioridadValue = prioridadCell.getNumericCellValue();
                    } else if (prioridadCell.getCellType() == CellType.STRING) {
                        try {
                            prioridadValue = Double.parseDouble(prioridadCell.getStringCellValue());
                        } catch (NumberFormatException ignored) {}
                    }
                }
                System.out.println("Procesando fila " + i + ", prioridad: " + prioridadValue);
                if (prioridadValue == 1) {
                    try {
                        String idConvocatoria = getCellValueAsString(row.getCell(0));

                        if (convocatoriaService.existeConvocatoria(idConvocatoria)) {
                            duplicados++;
                            continue;
                        }

                        Convocatoria convocatoria = new Convocatoria();
                        convocatoria.setIdConvocatoria(idConvocatoria);
                        convocatoria.setNombreEntidad(getCellValueAsString(row.getCell(1)));
                        convocatoria.setFechaPublicacion(parseDate(row.getCell(2)));
                        convocatoria.setTipoSeleccion(getCellValueAsString(row.getCell(3)));
                        convocatoria.setObjetoContratacion(getCellValueAsString(row.getCell(4)));
                        convocatoria.setDescripcion(getCellValueAsString(row.getCell(5)));
                        convocatoria.setAlcance(getCellValueAsString(row.getCell(6)));
                        convocatoria.setCantidad(parseInteger(row.getCell(7)));
                        convocatoria.setPlazoDias(parseInteger(row.getCell(8)));
                        convocatoria.setFechaConvocatoria(parseDate(row.getCell(9)));
                        convocatoria.setPrioridad((int) prioridadValue);
                        System.out.println("Fila " + i + " procesada: " + convocatoria.getPrioridad() + "ID: " + convocatoria.getIdConvocatoria());
                        convocatorias.add(convocatoria);

                        Cliente cliente = new Cliente();
                        cliente.setNroRuc(getCellValueAsString(row.getCell(1)));
                        clientes.add(cliente);

                        ObjetoContratacion objetoContratacion = new ObjetoContratacion();
                        objetoContratacion.setNomObj(getCellValueAsString(row.getCell(4)));
                        objetosContratacion.add(objetoContratacion);

                        Convocatorias convocatoria2 = new Convocatorias();
                        convocatoria2.setIdConvocatoria(idConvocatoria);
                        convocatoria2.setCliente(null);
                        convocatoria2.setFechaPublicacion(parseDate(row.getCell(2)));
                        convocatoria2.setTipoSeleccion(getCellValueAsString(row.getCell(3)));
                        convocatoria2.setObjetoContratacion(null);
                        convocatoria2.setDescripcion(getCellValueAsString(row.getCell(5)));
                        convocatoria2.setAlcance(getCellValueAsString(row.getCell(6)));
                        convocatoria2.setCantidad(parseInteger(row.getCell(7)));
                        convocatoria2.setPlazoDias(parseInteger(row.getCell(8)));
                        convocatoria2.setFechaConvocatoria(parseDate(row.getCell(9)));

                        //System.out.println("Fila " + i + " procesada: " + convocatoria.getPrioridad() + "ID: " + convocatoria.getIdConvocatoria());
                        convocatorias2.add(convocatoria2);
                    } catch (Exception e) {
                        System.out.println("Error procesando fila " + i + ": " + e.getMessage());
                    }
                }
            }
            System.out.println("Total de convocatorias con prioridad 1: " + convocatorias.size());
            System.out.println("Total de registros duplicados: " + duplicados); 
            if (convocatorias.isEmpty()) {
                return ResponseEntity.ok("No se encontraron convocatorias con prioridad 1.");   
            }
            for(Convocatoria convocatoria : convocatorias) {
                System.out.println("Convocatoria ID: " + convocatoria.getIdConvocatoria() + ", Prioridad: " + convocatoria.getPrioridad());
            }
            convocatoriaService.guardarConvocatorias(convocatorias);
            convocatoriaService.guardarConvocatorias2(convocatorias2);
            convocatoriaService.guardarClientes(clientes);
            convocatoriaService.guardarObjetosContratacion(objetosContratacion);

            return ResponseEntity.ok("Se guardaron " + convocatorias.size() + " convocatorias con prioridad 1. " +
                    "Se ignoraron " + duplicados + " registros duplicados.");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al procesar el archivo: " + e.getMessage());
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell) ?
                    new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue()) :
                    String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private Date parseDate(Cell cell) {
        if (cell == null) return null;

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }

        if (cell.getCellType() == CellType.STRING) {
            String dateStr = cell.getStringCellValue().trim();
            try {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateStr);
            } catch (Exception e1) {
                try {
                    return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
                } catch (Exception e2) {
                    return null;
                }
            }
        }

        return null;
    }

    private Integer parseInteger(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Integer.parseInt(cell.getStringCellValue());
            } catch (NumberFormatException ignored) {}
        }
        return null;
    }
}
