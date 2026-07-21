package com.agentspace.ai.prompt;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PromptTemplateService {

    public String replaceVariables(
            String template,
            Map<String, String> variables
    ) {

        String result = template;

        for (Map.Entry<String, String> entry : variables.entrySet()) {

            String placeholder =
                    "{{" + entry.getKey() + "}}";

            result = result.replace(
                    placeholder,
                    entry.getValue() == null ? "" : entry.getValue()
            );

        }

        return result;

    }

}