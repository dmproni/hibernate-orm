/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.metamodel.mapping.ordering.ast;

import org.hibernate.metamodel.mapping.PluralAttributeMapping;
import org.hibernate.metamodel.mapping.ordering.TranslationContext;
import org.hibernate.query.hql.internal.BasicDotIdentifierConsumer;

import org.jboss.logging.Logger;

/**
 * Represents the translation of an individual part of a path in `@OrderBy` translation
 *
 * Similar in purpose to {@link org.hibernate.query.hql.spi.DotIdentifierConsumer}, but for `@OrderBy` translation
 *
 * @author Steve Ebersole
 */
public class PathConsumer {
	private static final Logger log = Logger.getLogger( BasicDotIdentifierConsumer.class );

	private final TranslationContext translationContext;

	private final SequencePart rootSequencePart;

	private String pathSoFar;
	private SequencePart currentPart;

	public PathConsumer(
			PluralAttributeMapping pluralAttributeMapping, TranslationContext translationContext) {
		this.translationContext = translationContext;

		this.rootSequencePart = new RootSequencePart( pluralAttributeMapping );
	}

	public SequencePart getConsumedPart() {
		return currentPart;
	}

	public void consumeIdentifier(String identifier, boolean isBase, boolean isTerminal) {
		if ( isBase ) {
			// each time we start a new sequence we need to reset our state
			reset();
		}

		if ( pathSoFar == null ) {
			pathSoFar = identifier;
		}
		else {
			pathSoFar += ( '.' + identifier );
		}

		log.tracef(
				"BasicDotIdentifierHandler#consumeIdentifier( %s, %s, %s ) - %s",
				identifier,
				isBase,
				isTerminal,
				pathSoFar
		);

		currentPart = currentPart.resolvePathPart( identifier, isTerminal, translationContext );
	}

	private void reset() {
		pathSoFar = null;
		currentPart = rootSequencePart;
	}
}
