package com.perp.sasmodel.dynamicjdbc;

import oracle.jbo.JboException;

public class BlankUserNameOrPassword extends JboException  {
  public BlankUserNameOrPassword() {
    super("Blank username or password");
  }
}