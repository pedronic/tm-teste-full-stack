insert into tb_user values('90ac55d6-190b-4dcb-9567-9fb6ed86fd11', '$2a$10$zsKSrejnmJeGRfPWBhw8wewwIaVTpBN7aASSnEqLy5aFl1q1OU5Ci', 'user');
insert into tb_user values('559364c3-5481-4cf3-833d-2fa9d3acff48', '$2a$10$zsKSrejnmJeGRfPWBhw8wewwIaVTpBN7aASSnEqLy5aFl1q1OU5Ci', 'admin');

select * from tb_user;
select * from tb_role;
select * from tb_users_roles;

insert into tb_role values('f6308018-2edc-4c45-a9f7-4d499efbe475', 'ROLE_ADMIN');
insert into tb_role values('fb1033ed-814c-44dd-bedf-a444282416c9', 'ROLE_USER');

insert into tb_users_roles values('559364c3-5481-4cf3-833d-2fa9d3acff48','f6308018-2edc-4c45-a9f7-4d499efbe475');
insert into tb_users_roles values('90ac55d6-190b-4dcb-9567-9fb6ed86fd11', 'fb1033ed-814c-44dd-bedf-a444282416c9');

insert into tb_account values('9eaf42f7-487b-4a70-a6ee-d9961959d6dc', 000001, 1000.00, '90ac55d6-190b-4dcb-9567-9fb6ed86fd11');
insert into tb_account values('916de4f3-be9b-4fd2-9e64-5d56f10cc648', 000002, 2000.00, '90ac55d6-190b-4dcb-9567-9fb6ed86fd11');
insert into tb_account values('b7752f56-92cf-43ed-a750-ca4279fb99dd', 000003, 3000.00, '90ac55d6-190b-4dcb-9567-9fb6ed86fd11');

insert into tb_account values('aa6d556f-3813-450d-9b7f-ca86cce56894', 000010, 10000.00, '559364c3-5481-4cf3-833d-2fa9d3acff48');
insert into tb_account values('0b0c977f-a993-4ee5-949d-ebfe3da726e2', 000020, 20000.00, '559364c3-5481-4cf3-833d-2fa9d3acff48');
insert into tb_account values('84621190-fb87-4fb9-85c6-b1b007272c5d', 000030, 30000.00, '559364c3-5481-4cf3-833d-2fa9d3acff48');

insert into tb_fee values('67b5a11f-d3fe-433c-bfd0-92e9d31f2e01', 0.03, 3.00, 0, 0, 'A', 1000.00, 0.00);
insert into tb_fee values('08a95407-cf77-4147-b5b7-21224c4e4d7c', 0.00, 12.00, 10, 0, 'B', 2000.00, 1000.00);
insert into tb_fee values('bf7a7137-8812-42ea-a550-e9bbb5cee6e8', 0.082, 0.00, 19, 10, 'C1', 10000000.00, 2000.00);
insert into tb_fee values('9dd2791d-87ff-4304-89d3-a624378fcab2', 0.069, 0.00, 29, 20, 'C2', 10000000.00, 2000.00);
insert into tb_fee values('9efd6b4b-4dfb-489d-ba41-e42761cede5c', 0.047, 0.00, 39, 30, 'C3', 10000000.00, 2000.00);
insert into tb_fee values('4bd1e886-969e-4134-b9be-259f0500c9b1', 0.017, 0.00, 120, 40, 'C4', 10000000.00, 2000.00);

