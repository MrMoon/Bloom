# Bloom Database

```diff
- NOTE THAT THIS CONFIGURATION IS FOR POSTGRES DATABASES ONLY, I DON'T GURANTIE THE SAME RESULTS, OR CONFIGURATION ON OTHER KIND OF DATABASES
```

1) CREATE
    ```SQL
    -- Table: public.room
    -- DROP TABLE public.room;
    CREATE TABLE public.room (
        room_id bigint NOT NULL DEFAULT nextval('room_room_id_seq'::regclass),
        room_type text COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT room_pkey PRIMARY KEY (room_id)
    )
    TABLESPACE pg_default;
    ALTER TABLE public.room OWNER to bloom;
    
    -- Table: public.employee
    -- DROP TABLE public.employee;
    CREATE TABLE public.employee (
        employee_id bigint NOT NULL DEFAULT nextval('employee_employee_id_seq'::regclass),
        employee_name text COLLATE pg_catalog."default" NOT NULL,
        employee_job_type text COLLATE pg_catalog."default" NOT NULL,
        employee_salary double precision NOT NULL,
        employee_date_of_birth date NOT NULL,
        CONSTRAINT employee_pkey PRIMARY KEY (employee_id)
    )
    TABLESPACE pg_default;
    ALTER TABLE public.employee OWNER to postgres;
    
    -- Table: public.doctor
    -- DROP TABLE public.doctor;
    CREATE TABLE public.doctor (
        -- Inherited from table public.employee: employee_id bigint NOT NULL DEFAULT nextval('employee_employee_id_seq'::regclass),
        -- Inherited from table public.employee: employee_name text COLLATE pg_catalog."default" NOT NULL,
        -- Inherited from table public.employee: employee_job_type text COLLATE pg_catalog."default" NOT NULL,
        -- Inherited from table public.employee: employee_salary double precision NOT NULL,
        -- Inherited from table public.employee: employee_date_of_birth date NOT NULL,
        doctor_available_days text COLLATE pg_catalog."default",
        CONSTRAINT doctor_id PRIMARY KEY (employee_id)
    ) INHERITS (public.employee) TABLESPACE pg_default;
    ALTER TABLE public.doctor OWNER to postgres;
    
    -- Table: public.doctor_available_times
    -- DROP TABLE public.doctor_available_times;
    CREATE TABLE public.doctor_available_times (
        doctor_id bigint NOT NULL DEFAULT nextval('doctor_available_days_doctor_id_seq'::regclass),
        timing text COLLATE pg_catalog."default" NOT NULL,
        temp_id bigint NOT NULL DEFAULT nextval('doctor_available_days_temp_id_seq'::regclass),
        CONSTRAINT doctor_available_days_pkey PRIMARY KEY (temp_id),
        CONSTRAINT doctor_id FOREIGN KEY (doctor_id)
            REFERENCES public.doctor (employee_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
            NOT VALID
    )
    TABLESPACE pg_default;
    ALTER TABLE public.doctor_available_times OWNER to postgres;
    
    -- Table: public.fee
    -- DROP TABLE public.fee;
    CREATE TABLE public.fee (
        fee_payment_number bigint NOT NULL DEFAULT nextval('fee_fee_payment_number_seq'::regclass),
        patient_id bigint NOT NULL DEFAULT nextval('fee_patient_id_seq'::regclass),
        fee_amount double precision NOT NULL,
        fee_date date NOT NULL,
        CONSTRAINT fee_pkey PRIMARY KEY (fee_payment_number),
        CONSTRAINT patient_id FOREIGN KEY (patient_id)
            REFERENCES public.patient (patient_id) MATCH SIMPLE
            ON UPDATE CASCADE
            ON DELETE CASCADE
            NOT VALID
    )
    TABLESPACE pg_default;
    ALTER TABLE public.fee OWNER to postgres;
    
    -- Table: public.inventory
    -- DROP TABLE public.inventory;
    CREATE TABLE public.inventory (
        inventory_id bigint NOT NULL DEFAULT nextval('inventory_inventory_id_seq'::regclass),
        inventory_manged_by bigint NOT NULL DEFAULT nextval('inventory_inventory_manged_by_seq'::regclass),
        inventory_amount double precision NOT NULL,
        inventory_record text COLLATE pg_catalog."default" NOT NULL,
        inventory_record_type text COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT inventory_manged_by FOREIGN KEY (inventory_manged_by)
            REFERENCES public.nurse (employee_id) MATCH SIMPLE
            ON UPDATE CASCADE
            ON DELETE CASCADE
            NOT VALID
    )
    TABLESPACE pg_default;
    ALTER TABLE public.inventory OWNER to postgres;
    
    -- Table: public.nurse
    -- DROP TABLE public.nurse;
    CREATE TABLE public.nurse (
        -- Inherited from table public.employee: employee_id bigint NOT NULL DEFAULT nextval('employee_employee_id_seq'::regclass),
        -- Inherited from table public.employee: employee_name text COLLATE pg_catalog."default" NOT NULL,
        -- Inherited from table public.employee: employee_job_type text COLLATE pg_catalog."default" NOT NULL,
        -- Inherited from table public.employee: employee_salary double precision NOT NULL,
        -- Inherited from table public.employee: employee_date_of_birth date NOT NULL,
        nurse_rank text COLLATE pg_catalog."default" NOT NULL,
        nurse_shift text COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT nurse_id PRIMARY KEY (employee_id)
    ) INHERITS (public.employee) TABLESPACE pg_default;
    ALTER TABLE public.nurse OWNER to postgres;
    
    -- Table: public.patient
    -- DROP TABLE public.patient;
    CREATE TABLE public.patient (
        patient_id bigint NOT NULL DEFAULT nextval('patient_patient_id_seq'::regclass),
        patient_room_number bigint NOT NULL DEFAULT nextval('patient_patient_room_number_seq'::regclass),
        patient_assigned_doctor_id bigint NOT NULL DEFAULT nextval('patient_patient_assigned_doctor_id_seq'::regclass),
        patient_name text COLLATE pg_catalog."default" NOT NULL,
        patient_date_of_birth date NOT NULL,
        is_admitted boolean NOT NULL,
        gender text COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT patient_pkey PRIMARY KEY (patient_id),
        CONSTRAINT patient_doctor FOREIGN KEY (patient_assigned_doctor_id)
            REFERENCES public.doctor (employee_id) MATCH SIMPLE
            ON UPDATE CASCADE
            ON DELETE CASCADE
            NOT VALID,
        CONSTRAINT patient_room_id FOREIGN KEY (patient_room_number)
            REFERENCES public.room (room_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
            NOT VALID
    );
    
    -- Table: public.patient_entry
    -- DROP TABLE public.patient_entry;
    CREATE TABLE public.patient_entry (
        patient_entry_id bigint NOT NULL DEFAULT nextval('patient_entry_patient_entry_id_seq'::regclass),
        patient_id bigint NOT NULL DEFAULT nextval('patient_entry_patient_id_seq'::regclass),
        patient_entry_type text COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT patient_entry_pkey PRIMARY KEY (patient_entry_id),
        CONSTRAINT patient_id FOREIGN KEY (patient_id)
            REFERENCES public.patient (patient_id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
            NOT VALID
    )
    TABLESPACE pg_default;
    ALTER TABLE public.patient_entry OWNER to postgres;
    ```  

---

2) INSERT
    ```SQL 
	--- Room
	  INSERT INTO public.room(room_id, room_type) VALUES (?, ?);
    ```

---

3) UPDATE
    ```SQL
    ---Doctor
    UPDATE public.doctor SET employee_id=?, employee_name=?, employee_job_type=?, employee_salary=?, 
        employee_date_of_birth=?, doctor_available_days=? WHERE <condition>;

    ```

---

4) DELETE
    ```SQL
    --- Doctor
    DELETE FROM public.doctor WHERE <condition>;
    ```

---

5) Joins
    ```SQL
    --- Patient Name, Patient Gender, Room Type, and Room Number
    SELECT patient.patient_name, patient.gender, patient.patient_room_number, room.room_type
    FROM patient
    INNER JOIN room ON patient.patient_room_number = room.room_id;
    --- Nurse Name, Nurse Rank, Inventory Amount
    SELECT nurse.employee_name, nurse.nurse_rank, inventory.inventory_amount
    FROM nurse
    INNER JOIN inventory ON nurse.employee_id = inventory.inventory_manged_by;
    ```

---

6) Joins and Aggregations
    ```SQL
    --- Patient Fees
    SELECT SUM(fee.fee_amount)
    FROM fee
    INNER JOIN patient ON patient.patient_id = fee.patient_id;
    --- How many times the patient entered
    SELECT COUNT(patient_entry.patient_id)
    FROM patient_entry
    INNER JOIN patient ON patient.patient_id = patient_entry.patient_id;
    ```
