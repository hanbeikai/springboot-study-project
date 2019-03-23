-- 商品表
create TABLE tb_miaosha(
  goods_code VARCHAR(64) PRIMARY key not null comment '商品唯一编号',
  goods_nums int(255) DEFAULT null comment '商品余量'
)ENGINE=INNODB DEFAULT CHARSET=latin1 comment '商品表';