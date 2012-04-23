package com.si.mapbuilder.html;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * User: simonvandersluis
 * Date: 24/03/12
 * Time: 7:13 PM
 */
@Component
public class HtmlGenerator {

  @Resource
  private VelocityEngine velocityEngine;


  public File generateHtml(File targetDir) {
    Assert.isTrue(targetDir.isDirectory(), "HTML generation must be run over a directory");
    Map model = new HashMap();
    model.put("country", targetDir.getName());
    model.put("mapList", readMapImageFileNames(targetDir));
    String html = VelocityEngineUtils.mergeTemplateIntoString(
        velocityEngine, "html/map.vm", model);
    File htmlFile = createHtmlFile(targetDir);
    writeHtmlToFile(htmlFile, html);
    return htmlFile;
  }

  private void writeHtmlToFile(File file, String html) {
    try {
      FileWriter htmlWriter = new FileWriter(file);
      htmlWriter.append(html);
      htmlWriter.flush();
      htmlWriter.close();
    }
    catch (IOException e) {
      throw new RuntimeException("Unable to write to html file", e);
    }
  }

  private List<String> readMapImageFileNames(File targetDir) {
    List<String> mapImageFileNames = new ArrayList<String>();
    for (String fileName : targetDir.list()) {
      if (fileName.endsWith(".jpg")) {
        mapImageFileNames.add(fileName);
      }
    }
    Collections.sort(mapImageFileNames, new NumberedImageNameComparator());
    return mapImageFileNames;
  }

  private File createHtmlFile(File targetDir) {
    File html = new File(targetDir, targetDir.getName() + ".html");
    if (html.exists()) {
      html.delete();
    }
    try {
      html.createNewFile();
      return html;
    }
    catch (IOException e) {
      throw new RuntimeException("Unable to create html file "
          + html.getPath(), e);
    }
  }


}
