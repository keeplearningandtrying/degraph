package de.schauderhaft.dependencies.filter
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import com.jeantessier.dependency.ClassNode
import com.jeantessier.dependency.PackageNode
import de.schauderhaft.dependencies.categorizer.MultiCategorizer._

@RunWith(classOf[JUnitRunner])
class NoSelfReferenceTest extends FunSuite {
    import org.scalatest.matchers.ShouldMatchers._

    test("returns true for unrelated objects") {
        new NoSelfReference()("a", "b") should be(true)
    }

    test("returns false for identical objects") {
        new NoSelfReference()("a", "a") should be(false)
    }

    test("returns false if second object is contained in Categories of first instance") {
        new NoSelfReference(ListCategory("a", "b", "c", "d"))("a", "c") should be(false)
    }
    test("returns false if first object is contained in Categories of second instance") {
        new NoSelfReference(ListCategory("a", "b", "c", "d"))("c", "a") should be(false)
    }

    test("returns true for unrelated objects with categories") {
        new NoSelfReference(ListCategory("a", "b", "c", "d"))("a", "x") should be(true)
    }

    test("returns true for unrelated objects with common categorY") {
        new NoSelfReference(combine(ListCategory("a", "b"), ListCategory("x", "b")))("a", "x") should be(true)
    }
}