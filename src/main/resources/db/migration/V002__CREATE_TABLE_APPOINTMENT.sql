CREATE TABLE `appointment` (
    id CHAR(36) NOT NULL PRIMARY KEY,
    date_appointment TIMESTAMP NOT NULL,
    duration TIME NOT NULL,
    status VARCHAR(30) NOT NULL,
    observation VARCHAR(255) NULL,
    patient_id CHAR(36) NOT NULL,
    doctor_id CHAR(36) NOT NULL,

    CONSTRAINT fk_patient_id FOREIGN KEY (patient_id) REFERENCES `user`(id),
    CONSTRAINT fk_doctor_id FOREIGN KEY (doctor_id) REFERENCES `user`(id)
);