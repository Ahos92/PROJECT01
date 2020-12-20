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

insert into cart values (to_char(sysdate-30)||' '||'(0)', 50, 'ī����� (HOT)', 4, 10000, 1234);
insert into payment values (sysdate-30, '����', null, null, 10000, 5000, 5000, 0, null, 1234);

-- delete from payment where payment_date < '20/12/20';
-- delete from cart where saled_date < '20/12/20';

insert into branch values (1000, 123, '������', 'KN', '02-1111-2222');

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


select * from customer where last_name = '��';

SELECT * FROM payment where payment_type = 'CASH';

select * from customer where last_name||first_name like '%��%';

select * from payment where payment_date like '%16%';

--------------------------------------------------------------------------------
INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('202012', '12�� ����', 1000, '20/12/01', '20/12/31');

INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('202011', '11�� ����', 1000, '20/11/01', '20/11/30');
            
INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('201225', '��ź�� ����', 2000, '20/12/24', '20/12/25');
            
INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('200606', '��������', 500, '20/01/01', '20/06/06');
            
INSERT INTO 
    coupon (coupon_no, coupon_name, coupon_price, start_date, expired_date) 
VALUES
            ('201212', '�ܿ�����', 500, '20/06/07', '20/12/31');

INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('NH', '���� ����', '���� �߱� �����ȷ� 16 �����߾�ȸ �߾Ӻ���', '02-2080-5114');
        
INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('KB', '���� ����', '���� �������� ����������8�� 26', '1588-9999');

INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('SH', '���� ����', '���� �߱� �������9�� 20 �������� ����', '02-1577-8000');

INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('WR', '�츮 ����', '����Ư���� �߱� �Ұ��� 51', '1588-5000');

INSERT INTO
    bank (bank_id, bank_name, bank_location, phone_number)
VALUES
        ('IBK', '��� ����', '���� �߱� ������ 79', '1588-2588');