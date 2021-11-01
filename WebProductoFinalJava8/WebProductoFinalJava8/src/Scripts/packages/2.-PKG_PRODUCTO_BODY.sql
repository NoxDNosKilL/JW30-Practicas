create or replace PACKAGE BODY PKG_PRODUCTO AS

  PROCEDURE SP_LISTAR(
     P_CURSOR OUT SYS_REFCURSOR,
     P_NOMBRE IN  PRODUCTO.NOMBRE%TYPE,
     P_ID_USUARIO    IN  USUARIO.ID_USUARIO%TYPE
  ) AS
  BEGIN
  IF (P_ID_USUARIO=1) THEN
      
          OPEN 
                P_CURSOR
            FOR
                SELECT  
                        CODIGO,
                        NOMBRE,
                        PRECIO,
                        STOCK
                FROM 
                        PRODUCTO
                WHERE 
                            UPPER(NOMBRE) LIKE '%'||UPPER(P_NOMBRE)||'%'
                        AND ESTADO='1';
        ELSE 
        
          OPEN 
                P_CURSOR
            FOR
                SELECT  
                        CODIGO,
                        NOMBRE,
                        PRECIO,
                        STOCK
                FROM 
                        PRODUCTO
                WHERE 
                            UPPER(NOMBRE) LIKE '%'||UPPER(P_NOMBRE)||'%'
                        AND AUD_IDUSUARIO= P_ID_USUARIO
                        AND ESTADO='1';
        
        END IF;
  END SP_LISTAR;
  
  
  PROCEDURE SP_LISTAR_USUARIO
    (
        P_CURSOR        OUT SYS_REFCURSOR,
        P_ID_USUARIO    IN  USUARIO.ID_USUARIO%TYPE
    )AS
  BEGIN
        IF (P_ID_USUARIO=1) THEN
           OPEN 
                P_CURSOR
                    FOR
                        SELECT  
                                CODIGO,
                                NOMBRE,
                                PRECIO,
                                STOCK
                        FROM 
                                PRODUCTO
                        WHERE 
                                ESTADO='1';
          ELSE
              OPEN 
                        P_CURSOR
                    FOR
                        SELECT  
                                CODIGO,
                                NOMBRE,
                                PRECIO,
                                STOCK
                        FROM 
                                PRODUCTO
                        WHERE 
                                    AUD_IDUSUARIO= P_ID_USUARIO
                                AND ESTADO='1';
          END IF;
  
  END SP_LISTAR_USUARIO;
    
  
   PROCEDURE SP_LISTAR_PRECIOS
    (
        P_CURSOR    OUT SYS_REFCURSOR,
        P_NOMBRE    IN  PRODUCTO.NOMBRE%TYPE
    )AS
    BEGIN
      OPEN 
            P_CURSOR
        FOR
            SELECT 
                CODIGO,
                NOMBRE,
                PRECIO,
                FECHA,
                USUARIO,
                NOMBRE_USUARIO
            FROM 
                V_PRODUCTO_PRECIO
            WHERE
                 UPPER(NOMBRE) LIKE '%'||UPPER(P_NOMBRE)||'%';

    END SP_LISTAR_PRECIOS;

     PROCEDURE SP_LISTAR_PRECIOS_X_CODIGO
    (
        P_CURSOR    OUT SYS_REFCURSOR,
        P_CODIGO    IN  PRODUCTO.CODIGO%TYPE
    )AS
    BEGIN
      OPEN 
            P_CURSOR
        FOR
            SELECT 
                CODIGO,
                NOMBRE,
                PRECIO,
                FECHA,
                USUARIO,
                NOMBRE_USUARIO
            FROM 
                V_PRODUCTO_PRECIO
            WHERE
                 UPPER(CODIGO_PRODUCTO) =P_CODIGO;
    END SP_LISTAR_PRECIOS_X_CODIGO;
    
    PROCEDURE SP_BUSCAR_X_ID
    (
        P_CURSOR    OUT SYS_REFCURSOR,
        P_CODIGO    IN  PRODUCTO.CODIGO%TYPE
    )AS
    BEGIN
    OPEN 
            P_CURSOR
        FOR
            SELECT  
                    CODIGO,
                    NOMBRE,
                    PRECIO,
                    STOCK
            FROM 
                    PRODUCTO
            WHERE 
                    CODIGO  =  P_CODIGO;

    END SP_BUSCAR_X_ID;

  PROCEDURE SP_INSERTAR
    (
        P_CODIGO        OUT PRODUCTO.CODIGO%TYPE,
        P_NOMBRE        IN  PRODUCTO.NOMBRE%TYPE,
        P_PRECIO        IN  PRODUCTO.PRECIO%TYPE,
        P_STOCK         IN  PRODUCTO.STOCK%TYPE,
        -- Auditoria
        P_AUD_IDUSUARIO IN  PRODUCTO.AUD_IDUSUARIO%TYPE,
        P_AUD_SESION    IN  PRODUCTO.AUD_SESION%TYPE,
        P_AUD_IP        IN  PRODUCTO.AUD_IP%TYPE
    ) AS
  BEGIN

    SELECT
        SEQ_PRODUCTO.NEXTVAL
    INTO
        P_CODIGO
    FROM
        DUAL;

    INSERT INTO PRODUCTO(
        CODIGO,
        NOMBRE,
        PRECIO,
        STOCK,
        ESTADO,

        -- Auditoria
        AUD_TIPO,
        AUD_IDUSUARIO,
        AUD_SESION,
        AUD_IP,
        AUD_FECHA
    )
    VALUES
    (
        P_CODIGO,
        P_NOMBRE,
        P_PRECIO,
        P_STOCK,

        '1',
        -- Auditoria
        'I',
        P_AUD_IDUSUARIO,
        P_AUD_SESION,
        P_AUD_IP,
        sysdate
    );

  END SP_INSERTAR;

  PROCEDURE SP_ACTUALIZAR
    (
        P_CODIGO        IN  PRODUCTO.CODIGO%TYPE,
        P_NOMBRE        IN  PRODUCTO.NOMBRE%TYPE,
        P_PRECIO        IN  PRODUCTO.PRECIO%TYPE,
        P_STOCK         IN  PRODUCTO.STOCK%TYPE,
        -- Auditoria
        P_AUD_IDUSUARIO IN  PRODUCTO.AUD_IDUSUARIO%TYPE,
        P_AUD_SESION    IN  PRODUCTO.AUD_SESION%TYPE,
        P_AUD_IP        IN  PRODUCTO.AUD_IP%TYPE
    ) AS
  BEGIN

    UPDATE 
        PRODUCTO
    SET
        NOMBRE          =   P_NOMBRE,
        PRECIO          =   P_PRECIO,
        STOCK           =   P_STOCK,
        -- Auditoria
        AUD_TIPO        =   'A',
        AUD_IDUSUARIO   =   P_AUD_IDUSUARIO,
        AUD_SESION      =   P_AUD_SESION,
        AUD_IP          =   P_AUD_IP,
        AUD_FECHA       =   sysdate
    WHERE
        CODIGO          =   P_CODIGO;

  END SP_ACTUALIZAR;

    PROCEDURE SP_ELIMINAR
    (
        P_CODIGO        IN  PRODUCTO.CODIGO%TYPE,

        -- Auditoria
        P_AUD_IDUSUARIO IN  PRODUCTO.AUD_IDUSUARIO%TYPE,
        P_AUD_SESION    IN  PRODUCTO.AUD_SESION%TYPE,
        P_AUD_IP        IN  PRODUCTO.AUD_IP%TYPE
    ) AS
  BEGIN

    UPDATE 
        PRODUCTO
    SET
        ESTADO          =   '0',

        -- Auditoria
        AUD_TIPO        =   'E',
        AUD_IDUSUARIO   =   P_AUD_IDUSUARIO,
        AUD_SESION      =   P_AUD_SESION,
        AUD_IP          =   P_AUD_IP,
        AUD_FECHA       =   sysdate
    WHERE
        CODIGO          =   P_CODIGO;

  END SP_ELIMINAR;


END PKG_PRODUCTO;