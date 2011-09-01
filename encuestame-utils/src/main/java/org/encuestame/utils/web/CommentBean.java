/*
 ************************************************************************************
 * Copyright (C) 2001-2011 encuestame: system online surveys Copyright (C) 2011
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.utils.web;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Unit Comment bean.
 * @author Morales, Diana Paola paolaATencuestame.org
 * @since August 14, 2011
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentBean implements Serializable{

    /** Serial. **/
    private static final long serialVersionUID = 3553112539868437033L;

    /** **/
    @JsonProperty(value = "id")
    private Long commentId;

    /** **/
    @JsonProperty(value = "comment")
    private String comment;

    /** **/
    @JsonProperty(value = "created_at")
    private Date createdAt;

    /** **/
    @JsonProperty(value = "likeVote")
    private Long likeVote;

    /** **/
    @JsonProperty(value = "dislike_vote")
    private Long dislikeVote;

    /** **/
    @JsonProperty(value = "poll_id")
    private Long pollId;

    /** **/
    @JsonProperty(value = "user_id")
    private Long userAccountId;

    /** **/
    @JsonProperty(value = "tweetPoll_id")
    private Long tweetPoll;

    /** **/
    @JsonProperty(value = "parent_id")
    private Long parentId;

    /** **/
    @JsonProperty(value = "survey_id")
    private Long surveyId;

    /**
     * @return the commentId
     */
    @JsonIgnore
    public Long getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(final Long commentId) {
        this.commentId = commentId;
    }

    /**
     * @return the comment
     */
    @JsonIgnore
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(final String comment) {
        this.comment = comment;
    }

    /**
     * @return the createdAt
     */
    @JsonIgnore
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return the likeVote
     */
    @JsonIgnore
    public Long getLikeVote() {
        return likeVote;
    }

    /**
     * @param likeVote the likeVote to set
     */
    public void setLikeVote(final Long likeVote) {
        this.likeVote = likeVote;
    }

    /**
     * @return the unlikeVote
     */
    @JsonIgnore
    public Long getDislikeVote() {
        return dislikeVote;
    }

    /**
     * @param unlikeVote the unlikeVote to set
     */
    public void setDislikeVote(final Long dislikeVote) {
        this.dislikeVote = dislikeVote;
    }

    /**
     * @return the pollId
     */
    @JsonIgnore
    public Long getPollId() {
        return pollId;
    }

    /**
     * @param pollId the pollId to set
     */
    public void setPollId(final Long pollId) {
        this.pollId = pollId;
    }

    /**
     * @return the userAccountId
     */
    @JsonIgnore
    public Long getUserAccountId() {
        return userAccountId;
    }

    /**
     * @param userAccountId the userAccountId to set
     */
    public void setUserAccountId(final Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    /**
     * @return the tweetPoll
     */
    @JsonIgnore
    public Long getTweetPoll() {
        return tweetPoll;
    }

    /**
     * @param tweetPoll the tweetPoll to set
     */
    public void setTweetPoll(final Long tweetPoll) {
        this.tweetPoll = tweetPoll;
    }

    /**
     * @return the parentId
     */
    @JsonIgnore
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the surveyId
     */
    @JsonIgnore
    public Long getSurveyId() {
        return surveyId;
    }

    /**
     * @param surveyId the surveyId to set
     */
    public void setSurveyId(final Long surveyId) {
        this.surveyId = surveyId;
    }
}
