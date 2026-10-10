package com.ecommerce.automation.core;

import com.microsoft.playwright.Playwright;

public class PlayWrightFactory {
  public static Playwright createPlayWright(){
      return Playwright.create();
  }
}
