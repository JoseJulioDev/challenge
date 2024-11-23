package com.challenge.operations.service;

import com.challenge.operations.dto.RecordDTO;
import com.challenge.operations.entity.Operation;
import com.challenge.operations.entity.User;
import com.challenge.operations.exception.OperationNotFoundException;
import com.challenge.operations.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.challenge.operations.entity.Record;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public void save(Operation operation, User user, BigDecimal amount, BigDecimal userBalance, String response, String expression) {
        if (operation == null || user == null) {
            throw new IllegalArgumentException("Operation or user cannot be null.");
        }

        Record record = new Record();
        record.setOperation(operation);
        record.setUser(user);
        record.setAmount(amount);
        record.setUserBalance(userBalance);
        record.setOperationResponse(response);
        record.setExpression(expression);
        record.setDate(LocalDateTime.now());

        recordRepository.save(record);
    }

    public List<RecordDTO> getRecordsByUserId(Long userId) {
        List<Record> records = recordRepository.findByUserId(userId); // Supondo que você tenha esse método no repositório
        return convertToDTOList(records);
    }

    public void deleteOperation(Long id) {
        Optional<Record> record = recordRepository.findById(id);
        if (record.isPresent()) {
            recordRepository.deleteById(id);
        } else {
            throw new OperationNotFoundException("Record operation with ID " + id + " not found.");
        }
    }

    public RecordDTO convertToDTO(Record record) {
        RecordDTO dto = new RecordDTO();

        dto.setId(record.getId());
        dto.setAmount(record.getAmount());
        dto.setUserBalance(record.getUserBalance());
        dto.setOperationResponse(record.getOperationResponse());
        dto.setDate(record.getDate());
        dto.setExpression(record.getExpression());

        return dto;
    }

    public List<RecordDTO> convertToDTOList(List<Record> records) {
        return records.stream()
                .map(this::convertToDTO)
                .toList();
    }
}

