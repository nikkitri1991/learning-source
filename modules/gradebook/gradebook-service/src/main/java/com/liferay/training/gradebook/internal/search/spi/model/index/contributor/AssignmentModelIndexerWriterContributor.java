package com.liferay.training.gradebook.internal.search.spi.model.index.contributor;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * 
 * @author hgrahul
 * The Model Indexer Writer Contributor Configures The Re-Indexing And Batch Re-Indexing Behavior For Model Entity.
 */
@Component(immediate = true, property = "indexer.class.name=com.liferay.training.gradebook.model.Assignment", service = ModelIndexerWriterContributor.class)
public class AssignmentModelIndexerWriterContributor implements ModelIndexerWriterContributor<Assignment>{
	@Override
	public void customize(BatchIndexingActionable batchIndexingActionable, ModelIndexerWriterDocumentHelper modelIndexerWriterDocumentHelper) {
		batchIndexingActionable.setPerformActionMethod(
			(Assignment assignment) -> {
				Document documents = modelIndexerWriterDocumentHelper.getDocument(assignment);
				batchIndexingActionable.addDocuments(documents);
			}
		);
	}
	
	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return dynamicQueryBatchIndexingActionableFactory.getBatchIndexingActionable(assignmentLocalService.getIndexableActionableDynamicQuery());
	}
	
	@Override
	public long getCompanyId(Assignment assignment) {
		return assignment.getCompanyId();
	}
	
	@Reference
	private AssignmentLocalService assignmentLocalService;
	
	@Reference
	protected DynamicQueryBatchIndexingActionableFactory dynamicQueryBatchIndexingActionableFactory;
}

