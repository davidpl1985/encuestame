/*
 ************************************************************************************
 * Copyright (C) 2001-2011 encuestame: system online surveys Copyright (C) 2009
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.business.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.queryParser.ParseException;
import org.encuestame.business.search.AttachmentIndex;
import org.encuestame.business.search.IndexWriterManager;
import org.encuestame.business.search.IndexerFile;
import org.encuestame.business.search.UtilConvertToSearchItems;
import org.encuestame.core.search.GlobalSearchItem;
import org.encuestame.core.search.TypeSearchResult;
import org.encuestame.core.service.imp.SearchServiceOperations;
import org.encuestame.persistence.domain.Attachment;
import org.encuestame.persistence.exception.EnMeExpcetion;
import org.encuestame.persistence.exception.EnMeNoResultsFoundException;
import org.encuestame.utils.web.UnitAttachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Search Service.
 *
 * @author Morales, Diana Paola paolaATencuestame.org
 * @since February 09, 2011
 */
@Service
public class SearchService extends AbstractIndexService implements
        SearchServiceOperations {

    /**
     * Log.
     */
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * {@link IndexWriterManager}.
     */
    @Autowired
    private IndexWriterManager indexWriter; //TODO:ENCUESTAME-154

    /*
     * (non-Javadoc)
     * @see org.encuestame.business.service.imp.SearchServiceOperations#quickSearch(java.lang.String, java.lang.Integer, java.lang.Integer)
     */
    public Map<String, List<GlobalSearchItem>> quickSearch(String keyword,
            final Integer start, final Integer limit) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.encuestame.business.service.imp.SearchServiceOperations#quickSearch
     * (java.lang.String, java.lang.String)
     */
    public Map<String, List<GlobalSearchItem>> quickSearch(
            final String keyword,
            String language,
            final Integer start,
            final Integer limit,
            final List<TypeSearchResult> resultsAllowed)
            throws EnMeNoResultsFoundException, IOException, ParseException {
        final Map hashset = new HashedMap();
        if (resultsAllowed.indexOf(TypeSearchResult.QUESTION) != -1) {
            final List<GlobalSearchItem> questionResult = UtilConvertToSearchItems
                    .convertQuestionToSearchItem(retrieveQuestionByKeyword(keyword,
                            null));
            log.debug("questionResult " + questionResult.size());
            hashset.put("questions", questionResult);
        }

        if (resultsAllowed.indexOf(TypeSearchResult.PROFILE) != -1) {
            final List<GlobalSearchItem> profiles = UtilConvertToSearchItems
                    .convertProfileToSearchItem(getAccountDao().getPublicProfiles(keyword, limit, start));
            log.debug("profiles " + profiles.size());
            hashset.put("profiles", profiles);
        }

        if (resultsAllowed.indexOf(TypeSearchResult.HASHTAG) != -1) {
            final List<GlobalSearchItem> tags = UtilConvertToSearchItems
            .convertHashTagToSearchItem(getHashTagDao().getListHashTagsByKeyword(keyword, limit, null));
            log.debug("tags " + tags.size());
            hashset.put("tags", tags);
        }

        if (resultsAllowed.indexOf(TypeSearchResult.ATTACHMENT) != -1) {
            final List<GlobalSearchItem> attachments = UtilConvertToSearchItems
                                        .convertAttachmentSearchToSearchItem(getAttachmentItem(keyword, 10, "content"));
            log.debug("attachments " + attachments.size());
            hashset.put("attachments", attachments);
        }
       // List<GlobalSearchItem> totalItems = new ArrayList<GlobalSearchItem>(hashset);

        //TODO: order by rated or something.

        //filter my limit
        /*if (limit != null && start != null) {
            log.debug("split to "+limit  + " starting on "+start + " to list with size "+totalItems.size());
            totalItems = totalItems.size() > limit ? totalItems
                    .subList(start, limit) : totalItems;
        }
        //auto enumerate results.
        int x = 1;
        for (int i = 0; i < totalItems.size(); i++) {
            totalItems.get(i).setId(Long.valueOf(x));
            x++;
        }*/
        return hashset;
    }

    public List<GlobalSearchItem> globalKeywordSearch(String keyword,
            String language, final Integer start, final Integer limit) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<GlobalSearchItem> globalKeywordSearch(String keyword,
            final Integer start, final Integer limit) {
        // TODO Auto-generated method stub
        return null;
    }

    public String indexAttachment(final File file, final Long attachmentId){
     long start = System.currentTimeMillis();
               try {
                AttachmentIndex attachmentBean = IndexerFile.createAttachmentDocument(file, attachmentId);
                IndexerFile.addToIndex(attachmentBean, this.indexWriter);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                log.error(e);
            }
            long end = System.currentTimeMillis();
            log.debug("Indexing " + "numIndexed "+ " files took " + (end - start)
                    + " milliseconds");

        return "Attachment indexed";
    }

    /**
     *
     * @param unitAttachment
     * @throws EnMeExpcetion
     */
    public final void addAttachment(final UnitAttachment unitAttachment) throws EnMeExpcetion{
        try {
            Attachment attachment = new Attachment();
            attachment.setAttachmentId(unitAttachment.getAttachmentId());
            attachment.setFilename(unitAttachment.getFilename());
            attachment.setUploadDate(unitAttachment.getUploadDate());
            this.getProjectDaoImp().saveOrUpdate(attachment);
        } catch (Exception e) {
            throw new EnMeExpcetion(e);
        }
    }

}
