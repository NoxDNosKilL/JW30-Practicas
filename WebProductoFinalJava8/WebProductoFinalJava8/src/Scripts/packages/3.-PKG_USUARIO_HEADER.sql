create or replace PACKAGE PKG_USUARIO AS 

    PROCEDURE SP_LISTAR(
      P_CURSOR  OUT SYS_REFCURSOR,
      P_NOMBRE  IN  USUARIO.NOMBRE%TYPE,
      P_USUARIO IN  USUARIO.USUARIO%TYPE
    ); 
    
        PROCEDURE SP_VALIDAR_ACCESO(
      P_CURSOR  OUT SYS_REFCURSOR,
      P_USUARIO IN  USUARIO.USUARIO%TYPE,
      P_CLAVE   IN  USUARIO.CLAVE%TYPE
    ); 

END PKG_USUARIO;