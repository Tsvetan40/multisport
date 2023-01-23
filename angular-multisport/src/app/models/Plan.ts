import { RelaxCenter } from "./centers/RelaxCenter";
import { SportCenter } from "./centers/SportCenter";

export class Plan {
    private name: string
    private price: number
    private centersAddresses: string[]

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
}