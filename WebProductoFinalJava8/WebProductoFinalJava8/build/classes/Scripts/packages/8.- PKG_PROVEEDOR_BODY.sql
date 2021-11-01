create or replace PACKAGE BODY PKG_PROVEEDOR AS

  PROCEDURE SP_LISTAR(
      P_CURSOR          OUT SYS_REFCURSOR,
      P_RAZON_SOCIAL    IN  PROVEEDOR.RAZON_SOCIAL%TYPE,
      P_RUC             IN  PROVEEDOR.RUC%TYPE
    ) AS
  BEGIN
   
        OPEN 
            P_CURSOR
        FOR
            SELECT
                ID_PROVEEDOR,
                RAZON_SOCIAL,
                RUC,
                DIRECCION,
                CORREO,
                TELEFONO
            FROM
               PROVEEDOR
            WHERE
                (   UPPER(RAZON_SOCIAL)    LIKE '%'||  UPPER(P_RAZON_SOCIAL)||'%'
                    AND
                    UPPER(RUC)    LIKE '%'||  UPPER(P_RUC)||'%'
                )
             AND ESTADO  =   '1';
  END SP_LISTAR;
  
  PROCEDURE SP_INSERTAR(
      P_ID_PROVEEDOR    OUT PROVEEDOR.ID_PROVEEDOR%TYPE,
      P_RAZON_SOCIAL    IN  PROVEEDOR.RAZON_SOCIAL%TYPE,
      P_RUC             IN  PROVEEDOR.RUC%TYPE,
      P_DIRECCION       IN  PROVEEDOR.DIRECCION%TYPE,
      P_CORREO          IN  PROVEEDOR.CORREO%TYPE,
      P_TELEFONO        IN  PROVEEDOR.TELEFONO%TYPE,
      
      --
      P_AUD_IDUSUARIO   IN  PROVEEDOR.AUD_IDUSUARIO%TYPE,
      P_AUD_SESION      IN  PROVEEDOR.AUD_SESION%TYPE,
      P_AUD_IP          IN  PROVEEDOR.AUD_IP%TYPE
      
    )AS
    BEGIN
        SELECT
            SEQ_PROVEEDOR.NEXTVAL
        INTO
            P_ID_PROVEEDOR
        FROM
            DUAL;
        
        INSERT INTO 
        PROVEEDOR(
            ID_PROVEEDOR,
            RAZON_SOCIAL,
            RUC,
            DIRECCION,
            CORREO,
            TELEFONO,
            
            --
            ESTADO,
            --
            AUD_TIPO,
            AUD_IDUSUARIO,
            AUD_SESION,
            AUD_FECHA,
            AUD_IP
        )
        VALUES
        (
            P_ID_PROVEEDOR,
            P_RAZON_SOCIAL,
            P_RUC,
            P_DIRECCION,
            P_CORREO,
            P_TELEFONO,
            
            --
            '1',
            --
            'I',
            P_AUD_IDUSUARIO,
            P_AUD_SESION,
            SYSDATE,
            P_AUD_IP
        );
        
    END SP_INSERTAR;

  PROCEDURE SP_ACTUALIZAR(
      P_ID_PROVEEDOR    IN  PROVEEDOR.ID_PROVEEDOR%TYPE,
      P_RAZON_SOCIAL    IN  PROVEEDOR.RAZON_SOCIAL%TYPE,
      P_RUC             IN  PROVEEDOR.RUC%TYPE,
      P_DIRECCION       IN  PROVEEDOR.DIRECCION%TYPE,
      P_CORREO          IN  PROVEEDOR.CORREO%TYPE,
      P_TELEFONO        IN  PROVEEDOR.TELEFONO%TYPE,
      --
      P_AUD_IDUSUARIO   IN  PROVEEDOR.AUD_IDUSUARIO%TYPE,
      P_AUD_SESION      IN  PROVEEDOR.AUD_SESION%TYPE,
      P_AUD_IP          IN  PROVEEDOR.AUD_IP%TYPE
      
    )AS
    BEGIN
         UPDATE 
            PROVEEDOR
        SET
            RAZON_SOCIAL        =  P_RAZON_SOCIAL,
            RUC                 =  P_RUC,
            DIRECCION           =  P_DIRECCION,
            CORREO              =  P_CORREO,
            TELEFONO            =  P_TELEFONO,
            --
            AUD_TIPO            =   'A',
            AUD_IDUSUARIO       =   P_AUD_IDUSUARIO,
            AUD_SESION          =   P_AUD_SESION,
            AUD_FECHA           =   SYSDATE,
            AUD_IP              =   P_AUD_IP
        WHERE
            ID_PROVEEDOR    =   P_ID_PROVEEDOR;   
    END SP_ACTUALIZAR;
    
   PROCEDURE SP_ELIMINAR(
      P_ID_PROVEEDOR    IN  PROVEEDOR.ID_PROVEEDOR%TYPE,      
      --
      P_AUD_IDUSUARIO   IN  PROVEEDOR.AUD_IDUSUARIO%TYPE,
      P_AUD_SESION      IN  PROVEEDOR.AUD_SESION%TYPE,
      P_AUD_IP          IN  PROVEEDOR.AUD_IP%TYPE
      
    )AS
    BEGIN
        UPDATE 
            PROVEEDOR
        SET
            ESTADO              =   '0',
            --
            AUD_TIPO            =   'E',
            AUD_IDUSUARIO       =   P_AUD_IDUSUARIO,
            AUD_SESION          =   P_AUD_SESION,
            AUD_FECHA           =   SYSDATE,
            AUD_IP              =   P_AUD_IP
        WHERE
            ID_PROVEEDOR    =   P_ID_PROVEEDOR;   
    
    END SP_ELIMINAR;  

END PKG_PROVEEDOR;