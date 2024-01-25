-- =============================================================================
-- Base de datos: BIBLIOWEB
-- Fecha de creación: 16/08/2023
-- Descripción: Base de datos relacional del sistema web de libros, "Biblioweb".
-- =============================================================================

-- /////////////////////////////////////////CREACION DE LAS TABLAS

-- ##TABLAS, SEC.1 = DATA DEL USUARIO
 
CREATE TABLE PERSONAS_PERFIL(
CODIGOPE VARCHAR2(10),
NOMBRES VARCHAR2(50),
APELLIDOS VARCHAR2(50),
GENERO VARCHAR2(15),
FECH_NACIMIENTO VARCHAR2(15),
FOTOGRAFIA VARCHAR2(50)
);

ALTER TABLE PERSONAS_PERFIL ADD CONSTRAINT PK_CODIGOPE PRIMARY KEY (CODIGOPE);

CREATE TABLE ROLES(
CODIGOROL VARCHAR2(10),
NOMBRE VARCHAR2(50)
);

ALTER TABLE ROLES ADD CONSTRAINT PK_CODIGOROL PRIMARY KEY (CODIGOROL);

CREATE TABLE USUARIOS(
CODIGOUS VARCHAR2(10),
USERNAME VARCHAR2(20),
EMAIL VARCHAR2(255),
PASSWORD VARCHAR2(30),
CODIGOPE VARCHAR2(10)
);

ALTER TABLE USUARIOS ADD CONSTRAINT PK_CODIGOUS PRIMARY KEY (CODIGOUS);
ALTER TABLE USUARIOS ADD CONSTRAINT FK_CODIGOPE FOREIGN KEY (CODIGOPE) REFERENCES PERSONAS_PERFIL(CODIGOPE);
ALTER TABLE USUARIOS ADD CONSTRAINT UK_CODIGOPE UNIQUE (CODIGOPE);

CREATE TABLE USUARIOS_ROLES(
CODIGOUS VARCHAR2(10) NOT NULL,
CODIGOROL VARCHAR2(10) NOT  NULL
);

ALTER TABLE USUARIOS_ROLES ADD CONSTRAINT PK_CODIGOUSROL PRIMARY KEY (CODIGOUS, CODIGOROL);
ALTER TABLE USUARIOS_ROLES ADD CONSTRAINT FK_USROL_CODIGOUS FOREIGN KEY (CODIGOUS) REFERENCES USUARIOS (CODIGOUS) ENABLE;
ALTER TABLE USUARIOS_ROLES ADD CONSTRAINT FK_USROL_CODIGOROL FOREIGN KEY (CODIGOROL) REFERENCES ROLES (CODIGOROL) ENABLE;

-- ##TABLAS, SEC.2 = DATA DEL SISTEMA DE LIBROS

CREATE TABLE AUTORES(
CODIGOAUT VARCHAR2(10),
NOMBRES VARCHAR2(50),
APELLIDOS VARCHAR2(50),
FECH_NACIMIENTO VARCHAR2(15),
FOTOGRAFIA VARCHAR2(50),
EXTRA_INFO VARCHAR2(255)
);

ALTER TABLE AUTORES ADD CONSTRAINT PK_CODIGOAUT PRIMARY KEY (CODIGOAUT);

CREATE TABLE EDITORIALES(
CODIGOEDI VARCHAR2(10),
NOMBRE VARCHAR2(100)
);

ALTER TABLE EDITORIALES ADD CONSTRAINT PK_CODIGOEDI PRIMARY KEY (CODIGOEDI);

CREATE TABLE GENEROS(
CODIGOGEN VARCHAR2(10),
NOMBRE VARCHAR2(50)
);

ALTER TABLE GENEROS ADD CONSTRAINT PK_CODIGOGEN PRIMARY KEY (CODIGOGEN);

CREATE TABLE LIBROS(
CODIGOLIB VARCHAR2(10),
NOMBRE VARCHAR(255),
EDICION VARCHAR2(50),
LUGAR_PUBLICACION VARCHAR2(150),
FECH_PUBLICACION VARCHAR(15),
PAGINAS VARCHAR(10),
CODIGOEDI VARCHAR(10)
);

ALTER TABLE LIBROS ADD CONSTRAINT PK_CODIGOLIB PRIMARY KEY (CODIGOLIB);
ALTER TABLE LIBROS ADD CONSTRAINT FK_CODIGOEDI FOREIGN KEY (CODIGOEDI) REFERENCES EDITORIALES (CODIGOEDI);

CREATE TABLE LIBROS_GENEROS(
CODIGOLIB VARCHAR2(10) NOT NULL,
CODIGOGEN VARCHAR2(10) NOT NULL
);

ALTER TABLE LIBROS_GENEROS ADD CONSTRAINT PK_CODIGOLIBGEN PRIMARY KEY (CODIGOLIB, CODIGOGEN);
ALTER TABLE LIBROS_GENEROS ADD CONSTRAINT FK_LIBGEN_CODIGOLIB FOREIGN KEY (CODIGOLIB) REFERENCES LIBROS (CODIGOLIB) ENABLE;
ALTER TABLE LIBROS_GENEROS ADD CONSTRAINT FK_LIBGEN_CODIGOGEN FOREIGN KEY (CODIGOGEN) REFERENCES GENEROS (CODIGOGEN) ENABLE;

CREATE TABLE LIBROS_AUTORES(
CODIGOLIB VARCHAR2(10) NOT NULL,
CODIGOAUT VARCHAR2(10) NOT NULL
);

ALTER TABLE LIBROS_AUTORES ADD CONSTRAINT PK_CODIGOLIBAUT PRIMARY KEY (CODIGOLIB, CODIGOAUT);
ALTER TABLE LIBROS_AUTORES ADD CONSTRAINT FK_LIBAUT_CODIGOLIB FOREIGN KEY (CODIGOLIB) REFERENCES LIBROS (CODIGOLIB) ENABLE;
ALTER TABLE LIBROS_AUTORES ADD CONSTRAINT FK_LIBAUT_CODIGOGEN FOREIGN KEY (CODIGOAUT) REFERENCES AUTORES (CODIGOAUT) ENABLE;

-- ##TABLAS, SEC.3 = DATA DE FAVORITOS, INTERSECCION ENTRE LIBROS Y PERSONAS_PERFIL DE USUARIOS

CREATE TABLE FAVORITOS_LIBPER(
CODIGOUS VARCHAR2(10),
CODIGOLIB VARCHAR2(10)
);

ALTER TABLE FAVORITOS_LIBPER ADD CONSTRAINT PK_CODIGOPELIB PRIMARY KEY (CODIGOUS, CODIGOLIB);
ALTER TABLE FAVORITOS_LIBPER ADD CONSTRAINT FK_PELIB_CODIGOUS FOREIGN KEY (CODIGOUS) REFERENCES USUARIOS (CODIGOUS) ENABLE;
ALTER TABLE FAVORITOS_LIBPER ADD CONSTRAINT FK_PELIB_CODIGOLIB FOREIGN KEY (CODIGOLIB) REFERENCES LIBROS (CODIGOLIB) ENABLE;

-- /////////////////////////////////////////CREACION DE SEQUENCES

CREATE SEQUENCE AUTORES_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE EDITORIALES_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE GENEROS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE LIBROS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE PERSONAS_PERFIL_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE ROLES_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;
CREATE SEQUENCE USUARIOS_SEQ START WITH 1 INCREMENT BY 1 NOCACHE;

select * from roles;

select * from usuarios;
select * from personas_perfil;
select * from usuarios_roles;
select * from favoritos_libper;

select * from libros;
select * from editoriales;
select * from autores;
select * from generos;

select * from libros_autores;
select * from libros_generos;

select * from autores where upper(codigoaut) like upper('%aut%') or upper(nombres) like upper('%aut%') or upper(apellidos) like upper('%aut%') or upper(fech_nacimiento) like upper('%aut%');
select * from Generos where upper(codigogen) like upper('%sts1%') or upper(nombre) like upper('%st1%');
select * from Libros where upper(codigolib) like upper('%li%') or upper(nombre) like upper('%li%') or upper(edicion) like upper('%li%') or upper(lugar_publicacion) like upper('%li%') or upper(fech_publicacion) like upper('%li%') or upper(paginas) like upper('%li%');
select * from Personas_perfil where upper(codigope) like upper('%3%') or upper(nombres) like upper('%3%') or upper(apellidos) like upper('%3%') or upper(genero) like upper('%3%') or upper(fech_nacimiento) like upper('%3%');
select * from Roles where upper(codigorol) like upper('%us%') or upper(nombre) like upper('%us%');

select * from usuarios where upper(codigous) like upper('%us%') or upper(username) like upper('%us%') or upper(email) like upper('%us%');

select l.*, a.NOMBRES from libros l
inner join libros_autores lb on l.codigolib = lb.codigolib
inner join autores a on lb.codigoaut = a.codigoaut
where upper(l.codigolib) like upper('%3 nom%') or upper(l.nombre) like upper('%3 nom%') or upper(a.nombres) like upper ('%3 nom%');

select count(*) from libros l
inner join libros_autores lb on l.codigolib = lb.codigolib
inner join autores a on lb.codigoaut = a.codigoaut
where upper(l.codigolib) like upper('%3 nom%') or upper(l.nombre) like upper('%3 nom%') or upper(a.nombres) like upper ('%3 nom%');

select e.* from editoriales e where UPPER(e.codigoedi) like UPPER('%pens%') OR UPPER(e.nombre) like UPPER('%pens%');
select count(e.*) from editoriales e where upper(e.codigoedi) like upper('%pens%') or upper(e.nombre) like upper('%pens%');



select e.codigoedi as id, e.nombre as nombre from Editoriales e where upper(e.codigoedi) like upper('%'||?||'%') or upper(e.nombre) like upper('%'||?||'%') order by e.codigoedi asc;

select
        * 
    from
        (select
            e.codigoedi as id,
            e.nombre as nombre 
        from
            Editoriales e 
        where
            upper(e.codigoedi) like upper('%pens%') 
            or upper(e.nombre) like upper('%pens%') 
        order by
            id asc) 
    where
        rownum<=?;

select
        *
    from
        (select
            e1_0.codigoedi c0,
            e1_0.nombre c1,
            row_number() over(
        order by
            e1_0.codigoedi) rn 
        from
            editoriales e1_0 
        where
            upper(e1_0.codigoedi) like upper('%pens%') escape '\' 
            or upper(e1_0.nombre) like upper('%pens%') escape '\') r_0_ 
    where
        r_0_.rn<=?+?
        and r_0_.rn>?
    order by
        r_0_.rn;

--SELECT * FROM RESERVAS WHERE (FECH_ENTRADA) LIKE ('%27%') OR (FECH_SALIDA) LIKE ('%27%') OR (VALOR) LIKE ('%27%') OR UPPER(FORMAPAGO) LIKE UPPER ('%27%');

COMMIT;