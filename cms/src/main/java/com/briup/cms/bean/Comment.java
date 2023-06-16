package com.briup.cms.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.briup.cms.util.DateJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 评论
 * 
 * @author id
 *
 */
@Entity
@Table(name = "cms_comment")
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 编号（主键）
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 评论内容
	 */
	@Column(nullable = false)
	private String content;
	/**
	 * 评论时间
	 */
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date time;
	/**
	 * 评论人
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	/**
	 * 评论的文章
	 */
	@ManyToOne
	@JoinColumn(name = "article_id")
	private Article article;
	/**
	 * 该评论所评论的评论
	 */
	@ManyToOne
	@JoinColumn(name = "comment_id")
	private Comment comment;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JsonSerialize(using = DateJsonSerializer.class)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", time=" + time + ", user=" + user + ", article="
				+ article + ", comment=" + comment + "]";
	}

}
