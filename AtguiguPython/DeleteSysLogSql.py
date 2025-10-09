with open('E:\\工作\\科研服务平台\\需求\\20240911安徽省立科研新老升级\\备份\\Old\\Mysql\\sqlbackup\\business_ahslyy_dml_backup.sql', 'r', encoding='utf-8') as old_file:
    with open('E:\\工作\\科研服务平台\\需求\\20240911安徽省立科研新老升级\\备份\\Old\\Mysql\\sqlbackup\\business_ahslyy_dml_backup_clean.sql', 'w', encoding='utf-8') as new_file:
        line = old_file.readline()
        # 定位到需要删除的行
        while line:
            line = old_file.readline()
            if line.startswith('INSERT INTO `t_sys_oper_log`'):
                continue
            new_file.write(line)

