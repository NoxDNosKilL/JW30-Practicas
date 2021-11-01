CREATE OR REPLACE TRIGGER TRG_HISTORICO_PRECIO 
BEFORE UPDATE OF PRECIO ON PRODUCTO
FOR EACH ROW
BEGIN
  IF(:OLD.PRECIO <> :NEW.PRECIO) THEN
    INSERT INTO historico_precio
         (
            CODIGO,
            CODIGO_PRODUCTO,
            PRECIO,
            FECHA,
            ID_USUARIO,
            ESTADO
        )
    VALUES
         (  
            SEQ_HISTORICO_PRECIO.NEXTVAL,
            :OLD.CODIGO,
            :OLD.PRECIO,
            SYSDATE,
            :OLD.AUD_IDUSUARIO,
            '1'
        );
  END IF;
END;