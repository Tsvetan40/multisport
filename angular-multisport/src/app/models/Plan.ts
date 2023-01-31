export class Plan {
    private name: string
    private price: number
    private centersAddresses: string[]
    private imageBase64!: string

    constructor (name: string, price: number, centersAddresses: string[]) {
        this.name = name
        this.price = price
        this.centersAddresses = centersAddresses
    }

    public getCentersAddresses(): string[] {
        return this.centersAddresses
    }

    public getPrice(): number {
        return this.price
    }

    public getName(): string {
        return this.name
    }

    public setImageBase64 (imageBase64: string) {
        this.imageBase64 = imageBase64
    }

    public getImageBase64() {
        return this.imageBase64
    }
}