/*
    POS
*/
CREATE TABLE pos (
    device_id NUMBER(5) CONSTRAINT pos_did_pk PRIMARY KEY,
    branch_no NUMBER(5) CONSTRAINT pos_branch_nn NOT NULL,
    device_pw VARCHAR2(20) CONSTRAINT pos_dpw_nn NOT NULL
);

ALTER TABLE pos MODIFY (
    branch_no NUMBER(5) CONSTRAINT pos_branch_fk REFERENCES branch ( branch_no )
);

SELECT * FROM pos;
SELECT * FROM user_constraints WHERE table_name = 'POS';


/*
    BRANCH 
*/
-- 12/15 business_id 외래키 삭제 : 외래키 때문에 관리자 아이디 삭제가 안됨 / 지점 별 관리자는 그대로 유지
CREATE TABLE branch (
    branch_no NUMBER(5) CONSTRAINT brc_brn_pk PRIMARY KEY,
    business_id NUMBER(5) CONSTRAINT brc_bid_nn NOT NULL,
    branch_name VARCHAR2(20) CONSTRAINT brc_name_nn NOT NULL,
    branch_location VARCHAR2(3) CONSTRAINT brc_loc_nn NOT NULL,
    phone_NUMBER VARCHAR2(30) CONSTRAINT brc_tel_nn NOT NULL
);

SELECT * FROM branch;
SELECT * FROM user_constraints WHERE table_name = 'BRANCH';


/*
   BusinessAdminister
*/
-- 12/15 business_pw 추가 : 관리자 비밀번호
CREATE TABLE businessadminister (
    business_id NUMBER(5) CONSTRAINT ba_bid_pk PRIMARY KEY,
    business_pw VARCHAR2(20) CONSTRAINT ba_bpw_nn NOT NULL,
    last_name VARCHAR2(20) CONSTRAINT ba_lname_nn NOT NULL,
    first_name VARCHAR2(20) CONSTRAINT ba_fname_nn NOT NULL,
    contact_no VARCHAR2(30) CONSTRAINT ba_tel_nn NOT NULL
);

select * from businessadminister;
SELECT * FROM user_constraints WHERE table_name = 'BUSINESSADMINISTER';


/*
    CART
*/
-- 12/16 전체 자릿수 변경 
-- 12/18 saled_date : date -> timestamp 로 변경
-- 12/19 product_no NOT NULL제거, 외래키 제약조건에 on delete 추가, saled_date -> pk 
-- 12/20 외래키 삭제하고 product_no -> saled_product로 변경 ex) 상품이름(ICE) 표시위해
--       saled_date에 같은 날짜에 상품종류 구분을 위해 date타입 대신 varchar2로 변경 -> (1), (2) 표시위함
CREATE TABLE cart(
    saled_date VARCHAR(50) CONSTRAINT ca_sdate_pk PRIMARY KEY,
    order_no NUMBER(4) CONSTRAINT ca_odno_nn NOT NULL,
    saled_product_name VARCHAR(30) CONSTRAINT ca_name_nn NOT NULL,
    selected_item NUMBER(4) CONSTRAINT ca_sitem_nn NOT NULL,  
    total_price NUMBER(10) CONSTRAINT ca_price_nn NOT NULL,
    device_id NUMBER(5) CONSTRAINT ca_did_nn NOT NULL
);

SELECT * FROM cart;
SELECT * FROM user_constraints WHERE table_name = 'CART';


/*
    PRODUCT
*/
CREATE TABLE product(
    product_no NUMBER(2) PRIMARY KEY,
    product_name VARCHAR2(50) NOT NULL,
    product_price NUMBER(5) NOT NULL,
    product_count NUMBER(3),
    product_category VARCHAR2(10) NOT NULL,
    termsofcondition CHAR(3) check(termsofcondition IN('ICE', 'HOT'))
);

CREATE SEQUENCE product_seq START WITH 1 INCREMENT BY 1;

DELETE FROM PRODUCT WHERE product_no BETWEEN 1 and 99;

SELECT * FROM user_constraints WHERE table_name = 'PRODUCT';


/*
    BANK
*/
CREATE TABLE bank(
    bank_id CHAR(3) CONSTRAINT bk_id_pk PRIMARY KEY,
    bank_name VARCHAR2(20) CONSTRAINT bk_name_nn NOT NULL,
    bank_location VARCHAR2(100) CONSTRAINT bk_loc_nn NOT NULL,
    phone_NUMBER VARCHAR2(20) CONSTRAINT bk_pnum_nn NOT NULL
);

SELECT * FROM user_constraints WHERE table_name = 'BANK';


/*
    PAYMENT
*/
-- 12/18 전체수정 /  payment_date : date -> timestamp로 변경
-- 12/19 payy_seq 삭제, payment_no 삭제, payment_date -> pk
CREATE TABLE payment(
    payment_date timestamp primary key,
    payment_type varchar2(10) check(payment_type in('현금', '카드')),
    bank_id char(3),
    card_num varchar2(100),
    amount_of_money number(10),
    product_price number(10),
    actual_expenditure number(10) not null,
    usage_of_milage number(10),
    coupon_no char(6),
    device_id number(5) not null
);

ALTER TABLE payment MODIFY (
    coupon_no char(6) CONSTRAINT pay_cono_fk REFERENCES coupon (coupon_no),
    bank_id CHAR(3) CONSTRAINT pay_bid_fk REFERENCES bank (bank_id),
    device_id NUMBER(5) CONSTRAINT pay_did_fk REFERENCES pos (device_id)
);

SELECT * FROM payment;
SELECT * FROM user_constraints WHERE table_name = 'PAYMENT';


/*
    COUPON
*/
CREATE TABLE coupon(
    coupon_no char(6) CONSTRAINT cp_no_pk PRIMARY KEY,
    coupon_name VARCHAR2(100) CONSTRAINT cp_name_nn NOT NULL,
    coupon_price NUMBER(4) CONSTRAINT cp_prc_nn NOT NULL,
    start_date DATE CONSTRAINT cp_sdate_nn NOT NULL,
    expired_date DATE CONSTRAINT cp_edate_nn NOT NULL
);

SELECT * FROM user_constraints WHERE table_name = 'COUPON';


/*
    CUSTOMER
*/
-- 12/16 cusomer_no NUMBER -> VARCHAR2로 변경
CREATE TABLE customer (
    customer_no VARCHAR2(10) CONSTRAINT ctm_no_pk PRIMARY KEY,
    last_name VARCHAR2(10) CONSTRAINT ctm_lname_nn NOT NULL,
    first_name VARCHAR2(20) CONSTRAINT ctm_fname_nn NOT NULL,
    contact_no VARCHAR2(20) CONSTRAINT ctm_tel_nn NOT NULL,
    membership VARCHAR2(10)
                CONSTRAINT ctm_mem_ch CHECK(membership IN('bronze','silver', 'gold', 'platinum', 'diamond')),
    accumulation_pct NUMBER(2, 2) 
                CONSTRAINT ctm_acp_ch CHECK(accumulation_pct IN(0.01, 0.02, 0.03, 0.04, 0.05)),
    amount_price NUMBER(8),
    mileage NUMBER(6)
);
select * from customer;
SELECT * FROM user_constraints WHERE table_name = 'CUSTOMER';


/*
    DAILY_SALES_AMOUNT
*/
-- 12/18 하루 매출 저장 DB
CREATE TABLE daily_sales_amount(
    sales_date DATE CONSTRAINT dsa_date_pk PRIMARY KEY,
    total_amount NUMBER(10) CONSTRAINT dsa_mou_nn NOT NULL,
    device_id NUMBER(5) CONSTRAINT dsa_did_nn not null
);

ALTER TABLE daily_sales_amount MODIFY (
    device_id NUMBER(5) CONSTRAINT dsa_did_fk REFERENCES pos ( device_id )
);
