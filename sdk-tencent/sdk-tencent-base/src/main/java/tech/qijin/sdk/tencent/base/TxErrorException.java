package tech.qijin.sdk.tencent.base;

/**
 * @author Daniel Qian
 */
public class TxErrorException extends Exception {
  private static final long serialVersionUID = -6357149550353160810L;

  private TxError error;

  public TxErrorException(TxError error) {
    super(error.toString());
    this.error = error;
  }

  public TxErrorException(TxError error, Throwable cause) {
    super(error.toString(), cause);
    this.error = error;
  }

  public TxError getError() {
    return this.error;
  }


}
