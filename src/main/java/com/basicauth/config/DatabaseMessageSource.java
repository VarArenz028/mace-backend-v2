package com.basicauth.config;

import com.basicauth.data.MaceConfig;
import com.basicauth.service.MaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractMessageSource;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Rall Llobrera on 14/07/2017.
 */
public class DatabaseMessageSource extends AbstractMessageSource {

    @Autowired
    private MaceService maceService;

    private Locale defaultLocale = Locale.ENGLISH;
    private final String MESSAGE_SOURCE_CONFIG_TYPE = "MESSAGE_SOURCE";
    private HashMap<String,String> messageMap;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String message = messageMap.getOrDefault(code, "");
        return createMessageFormat(message, defaultLocale);
    }

    @PostConstruct
    public void init(){
        messageMap = new HashMap<>();
        messageMap.clear();
        List<MaceConfig> maceConfigs = maceService.getMaceConfigObjects(MESSAGE_SOURCE_CONFIG_TYPE);

        for(MaceConfig maceConfig : maceConfigs){
            messageMap.put(maceConfig.getKey1(), maceConfig.getValueContents());
        }
    }
}
