## 顺赢众筹管理数据库设计文档
##

### 目录
1.	介绍
1.	数据库详细设计
1.	附录

##
### 介绍
数据库采用mysql。

## 
### 数据库详细设计

#### 用户表 user
字段名              | 类型            | 长度 	| 主键 | 外键 | 是否能为 NULL | 描述
------------------ | --------------- | ---- 	| ---- |---- | ------------ | ---------------
user_id	           | int			 | 11	| Yes  |     | No           | 用户唯一id
password		   | varchar		 | 200	|      |     | No           | 用户密码
user_name		   | varchar		 | 200	|      |     | No           | 用户名字
payment			   | varchar		 | 200	|      |     | No           | 用户交款
ID_number		   | varchar		 | 200	|      |     | No           | 用户身份证号
handle_person	   | varchar		 | 200	|      |     | No           | 经手人
account_bank	   | varchar		 | 200	|      |     | No           | 开户银行
bank_account	   | varchar		 | 200	|      |     | No           | 银行账号
sex		   		   | varchar		 | 200	|      |     | No           | 性别
type		   	   | varchar		 | 200	|      |     | No           | 用户类型
address		   	   | varchar		 | 200	|      |     | No           | 地址
birthday		   | varchar		 | 200	|      |     | No           | 生日
telephone		   | varchar		 | 200	|      |     | No           | 电话
referrer_number	   | varchar		 | 200	|      |     | No           | 推荐人编号
add_time		   | varchar		 | 200	|      |     | No           | 登记时间

#### 项目表 project
字段名              | 类型            | 长度 	| 主键 | 外键 | 是否能为 NULL | 描述
------------------ | --------------- | ---- 	| ---- |---- | ------------ | ---------------
project_id         | int			 | 11	| Yes  |     | No           | 项目唯一id，自动增加
project_name	   | varchar		 | 200	|      |     | No           | 项目名字
introduce_text	   | varchar		 | 200	|      |     | YES          | 介绍文字
introduce_pic	   | varchar		 | 200	|      |     | YES          | 介绍图片url
predict_profit	   | varchar		 | 200	|      |     | No           | 预计利润
actual_profit	   | varchar		 | 200	|      |     | YES          | 实际利润
project_type	   | varchar		 | 200	|      |     | No           | 项目状态，0 为已结束，1为进行中

#### 项目预订表 project_order
字段名              | 类型            | 长度 	| 主键 | 外键 | 是否能为 NULL | 描述
------------------ | --------------- | ---- 	| ---- |---- | ------------ | ---------------
project_id         | int			 | 11	|      | Yes | No           | 项目唯一id，自动增加
user_id	           | int			 | 11	| 	   | Yes | No           | 用户唯一id
order_date	       | varchar		 | 200	|      |     | No           | 预订日期
order_content	   | varchar		 | 200	|      |     | No           | 预订内容
order_type	   	   | varchar		 | 200	|      |     | No           | 预订类型，0 为未确认，1 为已确认

#### 利润分配表 profit_distribution
字段名              | 类型            | 长度 	| 主键 | 外键 | 是否能为 NULL | 描述
------------------- | ---------------| ---- 	| ---- |---- | ------------ | ---------------
project_id          | int			 | 11	|      | Yes | No           | 项目唯一id，自动增加
user_id	            | int			 | 11	| 	   | Yes | No           | 用户唯一id
join_date	        | varchar		 | 200	|      |     | No           | 加入日期
join_days	   	    | varchar		 | 200	|      |     | No           | 加入总天数

## 
### 附录

#### mysql文件已转储为zhognchou.sql，导入即可使用。