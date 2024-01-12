--create database CmsFinal
---drop database CmsFinal
USE [master]
GO

CREATE DATABASE [CmsFinal] 
GO
USE [CmsFinal]
GO

create table [dbo].[Students](
	[StudentID] [int] IDENTITY(1,1) not null primary key,
	[account] [nvarchar](50) not null,
	[password] [nvarchar](50) not null,
	[name] [nvarchar](50) not null
)
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create table [dbo].[Categorys](
	[CateID] [int] IDENTITY(1,1) not null primary key,
	[name] [nvarchar](50) not null
)
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create table [dbo].[Class](
	[ClassID] [int] IDENTITY(1,1) not null primary key,
	[CateID] [int] foreign key REFERENCES [Categorys](CateID),
	[ClassName] [nvarchar](50) not null,
	[TeacherName] [nvarchar](50) not null
)
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create table [dbo].[List](
	[ListID] [int] IDENTITY(1,1) not null primary key,
	[StudentID] [int] foreign key references [Students](StudentID),
	[ClassID] [int] foreign key references [Class](ClassID)
)
go

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create table [dbo].[Document](
	[DocumentID] [int] IDENTITY(1,1) not null primary key,
	[DocumentLink] [nvarchar](50) not null
)
go
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create table [dbo].[ListDocument](
	[ListDocumentID] [int] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[ClassID] [int] foreign key references [Class](ClassID),
	[DocumentID] [int] foreign key references [Document](DocumentID)
)
go
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create table [dbo].[Question](
	[QuestionID] [int] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[QuestionDetail] [nvarchar](50) not null,
	[Choice1] [nvarchar](50) not null,
	[Choice2] [nvarchar](50) not null,
	[correct] [int] not null
)
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

update Question set val=0


create table [dbo].[Quizz](
	[QuizzID] [int] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[QuizzName] [nvarchar](50) not null,
	[ClassID] [int] foreign key references [Class](ClassID),
)
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ListQuestion](
	[ListQuestionID] [int] IDENTITY(1,1) NOT NULL PRIMARY KEY,
	[QuizzID] [int] foreign key references [Quizz](QuizzID),
	[QuestionID] [int] foreign key references [Question](QuestionID),
)
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO


SET IDENTITY_INSERT [dbo].[Students] ON
	insert [Students] (StudentID,account,password,name)VALUES(1,'admin','123','AD')
	insert [Students] (StudentID,account,password,name)VALUES(2,'nhatnthe176486','176486','NguyenThanhNhat')
	insert [Students] (StudentID,account,password,name)VALUES(3,'noname','00','NONE')
SET IDENTITY_INSERT [Students] OFF
GO

SET IDENTITY_INSERT [dbo].[Categorys] ON
	insert [Categorys] (CateID,name) VALUES(1,'Advanced Java')
	insert [Categorys] (CateID,name) VALUES(2,'Data Structure and Algorithm')
	insert [Categorys] (CateID,name) VALUES(3,'Introduction to Database')
	insert [Categorys] (CateID,name) VALUES(4,'Web Design')
SET IDENTITY_INSERT [dbo].[Categorys] OFF
GO

SET IDENTITY_INSERT [dbo].[Class] ON
	insert [Class] (ClassID,CateID,ClassName,TeacherName) VALUES(1,1,'PRJ301 SUMMER2023 - TienTD17','Ta Dinh Tien')
	insert [Class] (ClassID,CateID,ClassName,TeacherName) VALUES(2,1,'PRJ301 - SonNT5','Ngo Tung Son')
	insert [Class] (ClassID,CateID,ClassName,TeacherName) VALUES(3,2,'CSD201_ HungDC','Do Cong Hung')
	insert [Class] (ClassID,CateID,ClassName,TeacherName) VALUES(4,3,'DBI202 TuDQ5','Doan Quang Tu')
SET IDENTITY_INSERT [dbo].[Class] OFF
GO

SET IDENTITY_INSERT [dbo].[List] ON
	insert [List] (ListID,StudentID,ClassID) VALUES(1,1,2)
	insert [List] (ListID,StudentID,ClassID) VALUES(2,2,1)
	insert [List] (ListID,StudentID,ClassID) VALUES(3,2,3)
SET IDENTITY_INSERT [dbo].[List] OFF
GO

SET IDENTITY_INSERT [dbo].[Document] ON
	insert [Document] (DocumentID,DocumentLink) VALUES(1,'fb.com')
	insert [Document] (DocumentID,DocumentLink) VALUES(2,'youtube.com')
	insert [Document] (DocumentID,DocumentLink) VALUES(3,'https://chat.openai.com')
	insert [Document] (DocumentID,DocumentLink) VALUES(4,'https://stackoverflow.com/')
	insert [Document] (DocumentID,DocumentLink) VALUES(5,'https://www.w3schools.com/')
	insert [Document] (DocumentID,DocumentLink) VALUES(6,'https://www.instagram.com/')
SET IDENTITY_INSERT [dbo].[Document] OFF
GO	

SET IDENTITY_INSERT [dbo].[ListDocument] ON
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(1,1,1)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(2,1,2)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(3,1,3)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(4,1,4)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(5,1,5)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(6,1,6)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(7,2,1)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(8,2,3)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(9,2,5)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(10,3,2)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(11,3,4)
	insert [ListDocument] (ListDocumentID,ClassID,DocumentID) VALUES(12,4,6)
SET IDENTITY_INSERT [dbo].[ListDocument] OFF
GO  

SET IDENTITY_INSERT [dbo].[Question] ON
	insert [Question] (QuestionID,QuestionDetail,Choice1,Choice2,correct) VALUES(1,'Learn PRJ so easy?','True','False',2)
	insert [Question] (QuestionID,QuestionDetail,Choice1,Choice2,correct) VALUES(2,'1+1=?','2','3',1)
	insert [Question] (QuestionID,QuestionDetail,Choice1,Choice2,correct) VALUES(3,'You will pass PRJ301?','yes','no',1)
	insert [Question] (QuestionID,QuestionDetail,Choice1,Choice2,correct) VALUES(4,'Capital of VietNam?','Ha Noi','Ho Chi Minh',1)
	insert [Question] (QuestionID,QuestionDetail,Choice1,Choice2,correct) VALUES(5,'What animal u like?','dog','cat',2)
	insert [Question] (QuestionID,QuestionDetail,Choice1,Choice2,correct) VALUES(6,'What is the color of the Japanese flag?','red,white','black,blue',1)
SET IDENTITY_INSERT [dbo].[Question] OFF
GO


SET IDENTITY_INSERT [dbo].[Quizz] ON
	INSERT [Quizz] (QuizzID,QuizzName,ClassID) VALUES(1,'PT1',1)
	INSERT [Quizz] (QuizzID,QuizzName,ClassID) VALUES(2,'PT2',1)
	INSERT [Quizz] (QuizzID,QuizzName,ClassID) VALUES(3,'assigment',4)
	INSERT [Quizz] (QuizzID,QuizzName,ClassID) VALUES(4,'On FE',3)
SET IDENTITY_INSERT [dbo].[Quizz] OFF
go

SET IDENTITY_INSERT [dbo].[ListQuestion] ON
	insert [ListQuestion] (ListQuestionID,QuizzID,QuestionID) VALUES(1,1,1)
	insert [ListQuestion] (ListQuestionID,QuizzID,QuestionID) VALUES(2,1,2)
	insert [ListQuestion] (ListQuestionID,QuizzID,QuestionID) VALUES(3,1,3)
	insert [ListQuestion] (ListQuestionID,QuizzID,QuestionID) VALUES(4,2,4)
	insert [ListQuestion] (ListQuestionID,QuizzID,QuestionID) VALUES(5,2,5)
	insert [ListQuestion] (ListQuestionID,QuizzID,QuestionID) VALUES(6,2,6)
	insert [ListQuestion] (ListQuestionID,QuizzID,QuestionID) VALUES(7,3,5)
	insert [ListQuestion] (ListQuestionID,QuizzID,QuestionID) VALUES(8,3,3)
	insert [ListQuestion] (ListQuestionID,QuizzID,QuestionID) VALUES(9,4,2)
SET IDENTITY_INSERT [dbo].[ListQuestion] OFF
go

sp_rename 'Question.Chocie2', 'Choice2', 'COLUMN';

select q.QuestionID,QuestionDetail,Choice1,Choice2,correct from Question q join(select * from ListQuestion where QuizzID = 1) t on q.QuestionID = t.QuestionID
select QuestionDetail,Choice1,Chocie2,correct from Question q join (select * from ListQuestion where QuizzID = 1) t on q.QuestionID = t.QuestionID 