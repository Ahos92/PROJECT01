/*
    CUSTOMER
*/
CREATE TABLE customer (
    customer_no VARCHAR2(4) CONSTRAINT ctm_no_pk PRIMARY KEY,		-- 전화번호 뒷 4자리 저장하기 위해서 substring 사용 -> customer_no를 String으로 변경했습니다.
    last_name VARCHAR2(30) CONSTRAINT ctm_lname_nn NOT NULL,
    first_name VARCHAR2(30) CONSTRAINT ctm_fname_nn NOT NULL,
    contact_no VARCHAR2(20) CONSTRAINT ctm_tel_nn NOT NULL,
    amount_price NUMBER(8),
    membership VARCHAR2(10)
                CONSTRAINT ctm_mem_ch CHECK(membership IN('bronze','silver', 'gold', 'platinum', 'diamond')),
    accumulation_pct NUMBER(2, 2) 
                CONSTRAINT ctm_acp_ch CHECK(accumulation_pct IN(0.01, 0.02, 0.03, 0.04, 0.05)),
    mileage NUMBER(6)
);

CREATE SEQUENCE cus_seq START WITH 0 MINVALUE 0 MAXVALUE 999 INCREMENT BY 1 CYCLE NOCACHE;