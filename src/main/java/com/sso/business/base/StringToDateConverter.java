package com.sso.business.base;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import com.sso.yt.commons.exceptions.BusinessException;

/**
 * Created by yt on 2017-7-6.
 */
public class StringToDateConverter  implements Converter<String, Date> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String[] parsePatterns = { "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","yyyy/MM/dd HH:mm:ss","yyyy/MM/dd", "yyyyMMddHHmmss", "yyyyMMdd" };

	@Override
	public Date convert(String source) {
		if (!StringUtils.hasText(source)) {
			return null;
		}
		source = source.trim();
		try {
			if (source.matches("^\\d+$")) {
				Long lDate = new Long(source);
				return new Date(lDate);
			} else {
				Date date = DateUtils.parseDate(source, parsePatterns);
				return date;
			}
		} catch (Exception e) {
			logger.error(String.format("parser [%s] to Date fail", source), e);
		}
		throw new BusinessException(100000001, String.format("parser [%s] to Date fail", source));
	}

}
