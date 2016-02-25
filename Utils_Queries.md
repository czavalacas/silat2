```

update addclav cl set cl.c_clave = pkg_seguridad.get_hash('lcalixto','123','1') where cl.nid_clave = 2;
```

```

Para saber que usuarios y que roles tienen el permiso de pagar factura
select * from admperm;
select * from stdrope where nid_permiso = 57;
select * from stmrole;
select * from adduspe where nid_permiso = 57;

```