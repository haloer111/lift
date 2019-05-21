use lift;

-- 网页产品表
drop table if exists `web_product`;
CREATE TABLE `web_product`
(
  id                 BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  category_id        BIGINT COMMENT '类目id',
  relative_path      VARCHAR(255) COMMENT '媒体文件相对路径',
  type               INT COMMENT '媒体类型',
  title              VARCHAR(255) COMMENT '标题',
  description        VARCHAR(255) COMMENT '描述',
  detail             VARCHAR(255) COMMENT '详情',
  target_category_id BIGINT COMMENT '跳转类目id',
  accessory          VARCHAR(255) COMMENT '下载附件',
  sort               INT COMMENT '排序',
  create_time        DATETIME COMMENT '创建时间',
  update_time        DATETIME COMMENT '更新时间',
  PRIMARY KEY (id)
) COMMENT = '网页产品表 ';


-- 联系人表
drop table if exists `contact`;
CREATE TABLE `contact`
(
  id          BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  name        VARCHAR(255) COMMENT '联系人',
  tel         VARCHAR(255) COMMENT '手机号',
  address     VARCHAR(255) COMMENT '地址',
  create_time DATETIME COMMENT '创建时间',
  update_time DATETIME COMMENT '更新时间',
  PRIMARY KEY (id)
) COMMENT = '联系人表 ';

-- 类目表
drop table if exists `category`;
CREATE TABLE `category`
(
  id          BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
  name        VARCHAR(255) COMMENT '名称',
  parent_id   INT COMMENT '父id',
  sort        INT COMMENT '排序',
  status      INT COMMENT '状态',
  create_time DATETIME COMMENT '创建时间',
  update_time DATETIME COMMENT '更新时间',
  PRIMARY KEY (id)
) COMMENT = '类目表 ';


-- 用户表
drop table if exists `sys_user`;
create table `sys_user`
(
  id             varchar(64) primary key,
  name           varchar(64) unique key comment '用户名称',
  alias          varchar(64) comment '别名',
  tel            varchar(64) comment '手机号码',
  login_name     varchar(64) unique key comment '登录账号（唯一约束）',
  login_password varchar(64) comment '登录密码',
  create_time    datetime not null comment '创建时间',
  update_time    datetime not null comment '最近更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment = '用户表';
begin;
-- login：111，password：111
insert into sys_user(id, name, login_name, login_password, create_time, update_time)
VALUES ('1', '用户1', '111', '$2a$07$dIyptT/6/NHvx6ur29u3A.IdO3a5zGbh34L.PHnxK96ZITmk2TRhe', now(), now());
-- login：222，password：222
insert into sys_user(id, name, login_name, login_password, create_time, update_time)
VALUES ('2', '用户2', '222', '$2a$07$PrlVx.K9pEtDHolCt.xiw.FZDUTddxaJEpzXVrurmP2IB2VhVabWW', now(), now());
commit;

-- 角色表
drop table if exists `sys_role`;
create table `sys_role`
(
  id          varchar(64) primary key,
  name        varchar(64) comment '角色',
  description varchar(64) comment '描述',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '最近更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment = '角色表';
begin;
insert into `sys_role`
values ('1', 'default', '默认角色', now(), now());
insert into `sys_role`
values ('2', 'normal', '普通角色', now(), now());
commit;


-- 权限资源表
drop table if exists `sys_authority`;
create table `sys_authority`
(
  id          varchar(64) primary key,
  url         varchar(64) comment '资源路径（支持/*一级匹配、/**多级匹配）',
  http_method varchar(64) comment '请求类型httpMethod',
  description varchar(64) comment '描述',
  ordered     int comment '排序(数字越小越靠前)',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '最近更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment = '权限资源表';

begin;
insert into sys_authority(id, url, http_method, description, ordered, create_time, update_time)
VALUES ('1', '/**', '*', '全部模块', 1, now(), now());
insert into sys_authority(id, url, http_method, description, ordered, create_time, update_time)
VALUES ('2', '/user/test', 'get', '用户模块', 2, now(), now());
commit;

-- 用户角色关系表
drop table if exists `sys_user_role`;
create table `sys_user_role`
(
  id          varchar(64) primary key,
  user_id     varchar(64) comment '用户id',
  role_id     varchar(64) comment '角色id',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '最近更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment = '用户角色关系表';

begin;
insert into sys_user_role(id, user_id, role_id, create_time, update_time)
VALUES ('1', '1', '1', now(), now());
insert into sys_user_role(id, user_id, role_id, create_time, update_time)
VALUES ('2', '2', '2', now(), now());
commit;

-- 角色权限资源关系表
drop table if exists `sys_role_auth`;
create table `sys_role_auth`
(
  id          varchar(64) primary key,
  role_id     varchar(64) comment '角色id',
  auth_id     varchar(64) comment '权限资源id',
  create_time datetime not null comment '创建时间',
  update_time datetime not null comment '最近更新时间'
)
  engine = InnoDB
  default charset = utf8mb4
  comment = '角色权限资源关系表';

begin;
insert into sys_role_auth(id, role_id, auth_id, create_time, update_time)
values ('1', '1', '1', now(), now());
insert into sys_role_auth(id, role_id, auth_id, create_time, update_time)
values ('2', '2', '2', now(), now());
commit;

