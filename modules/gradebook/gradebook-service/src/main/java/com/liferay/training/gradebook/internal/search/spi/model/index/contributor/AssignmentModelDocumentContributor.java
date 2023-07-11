package com.liferay.training.gradebook.internal.search.spi.model.index.contributor;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.liferay.training.gradebook.model.Assignment;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * 
 * @author hgrahul
 * The Model Contributor Contributes or Controls Which Field Are Indexed.
 * This Class Contribute Method Is Called Each Time The Add or Update Method In Entity Service Layer Are Called.
 */
@Component(immediate = true, property = "indexer.class.name=com.liferay.training.gradebook.model.Assignment", service = ModelDocumentContributor.class)
public class AssignmentModelDocumentContributor implements ModelDocumentContributor<Assignment>{
	@Override
	public void contribute(Document document, Assignment assignment) {
		// Strip HTML Data
		String description = HtmlUtil.stripHtml(assignment.getDescription());
		document.addText(Field.DESCRIPTION, description);
		
		String title = HtmlUtil.stripHtml(assignment.getTitle());
		document.addText(Field.TITLE, title);
		
		document.addDate(Field.MODIFIED_DATE, assignment.getModifiedDate());
		
		// Handle Localized Data
		for(Locale locale : LanguageUtil.getAvailableLocales()) {
			String languageId = LocaleUtil.toLanguageId(locale);
			
			document.addText(LocalizationUtil.getLocalizedName(Field.DESCRIPTION, languageId), description);
			document.addText(LocalizationUtil.getLocalizedName(Field.TITLE, languageId), title);
		}
	}
}
