import { RelaxCenter } from "./centers/RelaxCenter";
import { SportCenter } from "./centers/SportCenter";

export class Plan {
    private name: string
    private price: number
    private relaxCenters: RelaxCenter[] = []
    private sportCenters: SportCenter[] = []

    constructor (name: string, price: number, relaxCenters: RelaxCenter[], sportCenters: SportCenter[]) {
        this.name = name
        this.price = price
        this.relaxCenters = relaxCenters
        this.sportCenters = sportCenters
    }

    public getSportCenters(): SportCenter[] {
        return this.sportCenters
    }

    public getRelaxCenters(): RelaxCenter[] {
        return this.relaxCenters
    }

    public getPrice(): number {
        return this.price
    }

    public getName(): string {
        return this.name
    }
}