/*
delete from payment;
delete from cart;
delete from daily_sales_amount;

drop table payment;
DROP TABLE cart;

drop sequence cart_seq;
create SEQUENCE cart_seq start with 1 INCREMENT by 1;

alter table cart modify saled_date timestamp;
*/
delete from payment;
delete from cart;

select * from product;
select * from payment order by Payment_date desc;
select * from branch;
select * from customer;
select * from bank;
select * from coupon;
select * from cart order by saled_date desc;
select * from daily_sales_amount;
select * from businessadminister;

insert into cart values (to_char(sysdate-30)||' '||'(0)', 50, '카페라테 (HOT)', 4, 10000, 1234);
insert into payment values (sysdate-30, '현금', null, null, 10000, 5000, 5000, 0, null, 1234);

-- delete from payment where payment_date < '20/12/20';
-- delete from cart where saled_date < '20/12/20';

insert into branch values (1000, 123, '강남점', 'KN', '02-1111-2222');

insert into pos values (1234, 1000, '1234');
insert into pos values (5678, 1000, '5678');

insert into businessadminister values (123, 45, 'Kim', 'YoungHo', '010-1234-1234');

select * from cart inner join product using(product_no) where saled_date like '%20/12/16%' order by cart_no asc;

select * from cart order by order_no desc;

delete from cart where saled_date like '%20/12/17%';

select sum(total_price) from cart where order_no = 1;

select sum(total_price) from cart where saled_date like '%20/12/17%';

select count(cart_no) from cart where order_no = 2;

select Max(order_no) from cart;


select * from customer where last_name = '김';

SELECT * FROM payment where payment_type = 'CASH';

select * from customer where last_name||first_name like '%김%';

select * from payment where payment_date like '%16%';

--------------------------------------------------------------------------------
INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('202012', '12월 쿠폰', 1000, '20/12/01', '20/12/31');

INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('202011', '11월 쿠폰', 1000, '20/11/01', '20/11/30');
            
INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('201225', '성탄절 쿠폰', 2000, '20/12/24', '20/12/25');
            
INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('200606', '여름쿠폰', 500, '20/01/01', '20/06/06');
            
INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('201212', '겨울쿠폰', 500, '20/06/07', '20/12/31');

INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('NH', '농협 은행', '서울 중구 새문안로 16 농협중앙회 중앙본부', '02-2080-5114');
        
INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('KB', '국민 은행', '서울 영등포구 국제금융로8길 26', '1588-9999');

INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('SH', '신한 은행', '서울 중구 세종대로9길 20 신한은행 본점', '02-1577-8000');

INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('WR', '우리 은행', '서울특별시 중구 소공로 51', '1588-5000');

INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('IBK', '기업 은행', '서울 중구 을지로 79', '1588-2588');
        
----------------------------------------------------------------------------------

insert into product VALUES(PRODUCT_SEQ.nextval,'아메리카노',4600,70,'Coffee','ICE');

insert into product VALUES(PRODUCT_SEQ.nextval,'아메리카노',4600,70,'Coffee','HOT');

insert into product VALUES(PRODUCT_SEQ.nextval,'카페라떼',5100,70,'Coffee','HOT');

insert into product VALUES(PRODUCT_SEQ.nextval,'카라멜마끼아또',6100,70,'Coffee','ICE');

insert into product VALUES(PRODUCT_SEQ.nextval,'카페모카',5600,70,'Coffee','HOT');

 

insert into product VALUES(PRODUCT_SEQ.nextval,'시그니처뱅쇼',6000,30,'Tea','ICE');

insert into product VALUES(PRODUCT_SEQ.nextval,'그린티',5500,30,'Tea','HOT');

insert into product VALUES(PRODUCT_SEQ.nextval,'허니레몬티',6100,30,'Tea','ICE');

insert into product VALUES(PRODUCT_SEQ.nextval,'얼그레이',5100,10,'Tea','HOT');

insert into product VALUES(PRODUCT_SEQ.nextval,'카모마일',5100,10,'Tea','HOT');

 

insert into product VALUES(PRODUCT_SEQ.nextval,'카라멜스콘',2900,30,'Deserts',null);

insert into product VALUES(PRODUCT_SEQ.nextval,'떠먹는티라미수',5900,20,'Deserts',null);

insert into product VALUES(PRODUCT_SEQ.nextval,'뉴욕치즈케잌 피스',5700,20,'Deserts',null);

insert into product VALUES(PRODUCT_SEQ.nextval,'가나슈 피스',5900,20,'Deserts',null);

insert into product VALUES(PRODUCT_SEQ.nextval,'당근케잌 피스',5700,15,'Deserts',null);

 

insert into product VALUES(PRODUCT_SEQ.nextval,'체리프라페',6500,50,'Frappe','ICE');

insert into product VALUES(PRODUCT_SEQ.nextval,'제주말차프라페',6300,50,'Frappe','ICE');

insert into product VALUES(PRODUCT_SEQ.nextval,'망고프라페',5800,50,'Frappe','ICE');

insert into product VALUES(PRODUCT_SEQ.nextval,'모카칩프라페',5800,50,'Frappe','ICE');

insert into product VALUES(PRODUCT_SEQ.nextval,'초코쉐이크',5500,50,'Frappe','ICE');