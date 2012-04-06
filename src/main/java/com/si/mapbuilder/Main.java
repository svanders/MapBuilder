package com.si.mapbuilder;

import com.si.mapbuilder.html.HtmlGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

/**
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:02 AM
 */
public class Main {


  public static void main(String[] args) {
    try {
      ApplicationContext app = loadApplicationContext();
      Options options = new Options(args);
      MapProcessor processor = new MapProcessor(options);
      File targetDir = processor.init();
      processor.run();
      System.out.println(processor.getFrames().size() + " frames generated from map");
      HtmlGenerator htmlGen = (HtmlGenerator) app.getBean(HtmlGenerator.class);
      htmlGen.generateHtml(targetDir);
      System.exit(0);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  private static ApplicationContext loadApplicationContext() {
    return new ClassPathXmlApplicationContext("/applicationContext.xml");
  }
}
