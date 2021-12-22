package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {
	@Id	//Primary Key
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REPLY_SEQ_GENERATOR")
	@SequenceGenerator(name="REPLY_SEQ_GENERATOR", sequenceName="REPLY_SEQ", initialValue=1, allocationSize=1)
	private int id;
		
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne		// Many = Reply, One = Board
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne		// Many = Reply, One = Blogger
	@JoinColumn(name="userId")
	private Blogger user;
	
	@CreationTimestamp
	private Timestamp createDate;

	@Override
	public String toString() {
		return "Reply [id=" + id + ", content=" + content + ", board=" + board + ", user=" + user + ", createDate="
				+ createDate + "]";
	}
	
}
