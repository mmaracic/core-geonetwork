package org.fao.geonet.kernel.search;

import org.fao.geonet.kernel.SchemaManager;
import org.fao.geonet.utils.Xml;
import org.jdom.Element;
import org.jdom.JDOMException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public final class LabelCacheLoader implements Callable<Map<String, String>> {
    private final String langCode;
    private final String labelName;
    private final SchemaManager schemaManager;

    public LabelCacheLoader(String langCode, String labelName, SchemaManager schemaManager) {
        this.langCode = langCode;
        this.labelName = labelName;
        this.schemaManager = schemaManager;
    }

    @Override
    public Map<String, String> call() throws Exception {
        Map<String, String> _codeList = new HashMap<String, String>();
        Set<String> schemas = schemaManager.getSchemas();
        for (String schema : schemas) {
            
            Path schemaDir = schemaManager.getSchemaDir(schema);
            addCodeLists(labelName, _codeList, schemaDir.resolve("loc").resolve(langCode).resolve("labels.xml"));
        }
        return _codeList;
    }

    public static String cacheKey(final String langCode, final String codeListName) {
        return "codelist:"+langCode+":"+codeListName;
    }

    @SuppressWarnings("unchecked")
    private void addCodeLists(String labelName, Map<String, String> codeList, Path file) throws IOException, JDOMException {
        if (Files.exists(file)) {
            
            String name=null, context=null, supercontext=null;
            String[] parts = labelName.split("/");
            if (parts.length>=1){
                name = parts[parts.length-1];
            }
            if (parts.length>=2){
                context = parts[parts.length-2];
            }
            if (parts.length>=3){
                supercontext = parts[parts.length-3];
            }
            
            Element xmlDoc = Xml.loadFile(file);

            List<Element> elements = xmlDoc.getChildren("element");
            for (Element element : elements) {
                if ((element.getAttributeValue("name").equals(name)) &&
                        (context == null || element.getAttributeValue("context").equals(context)) &&
                        (supercontext == null || element.getAttributeValue("supercontext").equals(supercontext))){
                    List<Element> helpers = element.getChildren("helper");
                    for(Element helper : helpers){
                        List<Element> options = helper.getChildren("option");
                        for (Element option : options) {
                            codeList.put(option.getAttributeValue("value"), option.getText());
                        }
                    }
                    break;
                }
            }
        }
    }

}