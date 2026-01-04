package fr.fortyTwo.printer.logic;

import com.beust.jcommander.Parameter;

public class Args {

  @Parameter(names = "--white", description = "Color for white pixels", required = true)
  private String white;

  @Parameter(names = "--black", description = "Color for black pixels", required = true)
  private String black;

  public String getWhite() {
    return this.white;
  }

  public String getBlack() {
    return this.black;
  }
}

