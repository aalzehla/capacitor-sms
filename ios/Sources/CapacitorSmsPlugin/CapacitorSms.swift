import Foundation

@objc public class CapacitorSms: NSObject {
    @objc public func send(_ value: String) -> String {
        print(value)
        return value
    }
}
