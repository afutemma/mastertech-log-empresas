package com.mastertech.empresalog.consumer;

import com.mastertech.receita.dto.EmpresaDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;

@Component
public class EmpresaConsumer {

    @KafkaListener(topics = "spec3-amanda-futemma-3", groupId = "shirley-mariel-bernadete")
    public void receber(@Payload EmpresaDTO empresa) {

        System.out.println("Consumer recebeu de spec3-amanda-futemma-3");
        File f = new File("/home/a2w/Documentos/logsEmpresa.csv");

        try {

            FileWriter csvWriter = new FileWriter(f);

            csvWriter.append(empresa.getCnpj());
            csvWriter.append(",");
            csvWriter.append(empresa.getNome());
            csvWriter.append(",");
            csvWriter.append(Double.toString(empresa.getCapital()));
            csvWriter.append("\n");

            csvWriter.flush();
            csvWriter.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
