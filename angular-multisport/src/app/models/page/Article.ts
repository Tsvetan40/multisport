export class Article {
    private title: string
    private content: string
    private pictureBase64: string

    constructor() {
        this.title = ''
        this.content = ''
        this.pictureBase64 = ''
    }

    withTitle(title: string): Article {
        this.title = title
        return this
    }

    withContent(content: string): Article {
        this.content = content
        return this
    }

    withPictureBase64(pictureBase64: string): Article {
        this.pictureBase64 = pictureBase64
        return this
    }

    getTitle(): string {
        return this.title
    }

    getContent(): string {
        return this.content
    }

    getImageBase64() : string {
        return this.pictureBase64
    }
}