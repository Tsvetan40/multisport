export class Article {
    private title: string
    private content: string

    constructor(title: string, content: string) {
        this.title = title
        this.content = content
    }

    getTitle(): string {
        return this.title
    }

    getContent(): string {
        return this.content
    }

}