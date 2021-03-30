package ua.com.alevel.model;


import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //максимальная длина 100 символов
    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content", length = 1000)
    //максимальная длина 1000 символов
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    //автор сатьи
    private User author;

    @OneToOne(cascade = CascadeType.ALL)
    //модератор (тот кто апрувит статью)
    private User moderator;

    @Column(name = "rating")
    //рейтинг статьи, может быть как отрицательный так и положительный
    private int rating;

    @Enumerated(EnumType.STRING)
    private PostStatus status;
}
