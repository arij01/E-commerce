package org.example.service;

import org.example.entity.EmailDetails;
import org.example.exception.EmailDetailsNotFoundException;
import org.example.repository.EmailDatabaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailDatabaseServiceImpl implements EmailDatabaseService{
    private Logger LOGGER = LoggerFactory.getLogger(EmailDatabaseServiceImpl.class);

    @Autowired
    private EmailDatabaseRepository repository;

    @Override
    public EmailDetails addEmailInfoToDB(EmailDetails emailDetailsRecord) {
        EmailDetails entity = repository.save(emailDetailsRecord);
        LOGGER.info(String.format("new Email Details record added to database successfully"));
        return entity;
    }

    @Override
    public void updateEmailDetailsStatus(EmailDetails updatedEmailDetailsRecord, Integer id) {
        EmailDetails existingRecord = repository.findById(id).orElse(null);

        if(existingRecord == null){
            LOGGER.info(String.valueOf(new EmailDetailsNotFoundException("record not found with id:" + id)));
            return;
        }
        existingRecord.setStatus(updatedEmailDetailsRecord.getStatus());
        existingRecord.setReceived_at(updatedEmailDetailsRecord.getReceived_at());

        repository.save(existingRecord);

        LOGGER.info(String.format("Email Details record updated successfully"));
    }

    @Override
    public List<EmailDetails> getFailedEmails() {
        return null;
    }
}