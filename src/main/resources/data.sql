INSERT INTO tb_order_status (status_name)
SELECT 'PLACED'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_order_status WHERE status_name = 'PLACED'
);


INSERT INTO tb_order_status (status_name)
SELECT 'DELIVERED'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_order_status WHERE status_name = 'DELIVERED'
);

INSERT INTO tb_order_status (status_name)
SELECT 'CANCELLED'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_order_status WHERE status_name = 'CANCELLED'
);

INSERT INTO tb_order_status (status_name)
SELECT 'PENDING'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_order_status WHERE status_name = 'PENDING'

);

INSERT INTO tb_order_status (status_name)
SELECT 'FAILED'
WHERE NOT EXISTS (
    SELECT 1 FROM tb_order_status WHERE status_name = 'FAILED'
);