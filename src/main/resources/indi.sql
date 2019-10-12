CREATE TABLE account (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL COMMENT '姓名',
  `balance` INT NOT NULL DEFAULT 0 COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY udx_name(`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO account (name, balance) VALUES ('zhangsan', 1000);
INSERT INTO account (name, balance) VALUES ('lisi', 1000);
